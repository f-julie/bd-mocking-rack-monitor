package com.amazon.ata.mocking.rackmonitor;

import com.amazon.ata.mocking.rackmonitor.clients.warranty.Warranty;
import com.amazon.ata.mocking.rackmonitor.clients.warranty.WarrantyClient;
import com.amazon.ata.mocking.rackmonitor.clients.warranty.WarrantyNotFoundException;
import com.amazon.ata.mocking.rackmonitor.clients.wingnut.WingnutClient;
import com.amazon.ata.mocking.rackmonitor.clients.wingnut.WingnutClientException;
import com.amazon.ata.mocking.rackmonitor.clients.wingnut.WingnutServiceException;
import com.amazon.ata.mocking.rackmonitor.exceptions.NoSuchServerException;
import com.amazon.ata.mocking.rackmonitor.exceptions.RackMonitorDependencyException;
import com.amazon.ata.mocking.rackmonitor.exceptions.RackMonitorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.amazon.ata.mocking.rackmonitor.RequestAction.INSPECT;
import static com.amazon.ata.mocking.rackmonitor.RequestAction.REPLACE;

/**
 * A program that monitors the health of racks in a data center.
 * When called repeatedly, it will file a Wingnut maintenance
 * request as soon as a server fails or needs inspections.
 */
public class RackMonitor {
    private Logger logger = LogManager.getLogger(RackMonitor.class);
    private final double inspectHealth;
    private final double replaceHealth;
    private final Set<Rack> racks;
    private final WingnutClient wingnutClient;
    private final WarrantyClient warrantyClient;
    private final Set<HealthIncident> incidents = new HashSet<>();

    public RackMonitor(Set<Rack> racks,
                       WingnutClient wingnutClient,
                       WarrantyClient warrantyClient,
                       double inspectHealth,
                       double replaceHealth) {

        this.racks = racks;
        this.wingnutClient = wingnutClient;
        this.warrantyClient = warrantyClient;
        this.inspectHealth = inspectHealth;
        this.replaceHealth = replaceHealth;
    }

    /**
     * Checks all the servers in all the racks, filing requests with
     * Wingnut if any of them aren't healthy. Does not file repeat
     * requests; keeps track of successful requests.
     *
     * @throws RackMonitorDependencyException If Wingnut or Warranty fail.
     * @throws RackMonitorException If something goes wrong with our logic.
     */
    public void monitorRacks() throws RackMonitorDependencyException, RackMonitorException {
        logger.info("monitorRacks(): checking all servers in {} racks", racks.size());
        // Monitor all servers in all racks
        for (Rack rack : racks) {
            // Get the health of servers in this rack
            Map<Server, Double> healthReport = rack.getHealth();
            for (Map.Entry<Server, Double> serverHealth : healthReport.entrySet()) {
                Double health = serverHealth.getValue();
                Server server = serverHealth.getKey();
                if (health < replaceHealth) {
                    // Server should be replaced!
                    arrangeReplacement(rack, server);
                } else if (health < inspectHealth) {
                    // Server should be inspected soon
                    arrangeInspection(rack, server);
                }
                // else server needs no attention
            }
        }
    }

    /**
     * Returns all the HealthIncidents reported to Wingnut since
     * this RackMonitor started.
     * @return all the HealthIncidents reported to Wingnut since
     * this RackMonitor started.
     */
    public Set<HealthIncident> getIncidents() {
        logger.info("getIncidents(): {} incidents reported since startup", incidents.size());
        return new HashSet<>(this.incidents);
    }

    /**
     * Request a replacement for the server. Looks up the unit and
     * warranty for the provided Server (in the provided Rack) so we can
     * file a request with Wingnut. If a request to replace the server
     * has already been made, does not repeat the request.
     * @param rack The Rack the server is in.
     * @param server The Server that needs to be replaced.
     * @throws RackMonitorException if the Server has no warranty.
     * @throws RackMonitorDependencyException if either Wingnut or the
     *         warranty service fails.
     */
    public void arrangeReplacement(Rack rack, Server server)
        throws RackMonitorException, RackMonitorDependencyException {

        // Get the unit slot of the Server, or throw NoSuchServerException
        int unit = getUnit(rack, server);

        // Check for duplicate requests.
        HealthIncident incident = new HealthIncident(server, rack, unit, REPLACE);
        if (incidents.contains(incident)) {
            logger.info("Already requested REPLACE for {} in {} unit: {}", server, rack, unit);
            return;
        }

        // Get the Warranty for this Server. Use the nullWarranty
        // instead if the actual Warranty has expired.
        Warranty warranty;
        try {
            logger.info("Retrieving warranty for {}", server);
            warranty = warrantyClient.getWarrantyForServer(server);
        } catch (WarrantyNotFoundException e) {
            String msg = String.format(
                "Rack %s unit %d manages server %s with no warranty!",
                rack, unit, server);
            logger.fatal(msg, e);
            throw new RackMonitorException(msg, e);
        }

        if (warranty.hasExpired()) {
            logger.info("Replacing expired warranty for {} with nullWarranty", server);
            warranty = Warranty.nullWarranty();
        }

        // Actually request the replacement
        try {
            logger.info("Requesting REPLACE for {} in {} unit: {} under {}",
                server, rack, unit, warranty);
            wingnutClient.requestReplacement(rack, unit, warranty);
        } catch (WingnutClientException e) {
            logger.warn("Bad request to REPLACE for {} in {} unit: {} under {}",
                server, rack, unit, warranty, e);
            throw new RackMonitorException(e);
        } catch (WingnutServiceException e) {
            logger.warn("Wingnut failed request to REPLACE for {} in {} unit: {} under {}",
                server, rack, unit, warranty, e);
            throw new RackMonitorDependencyException(e);
        }

        // Remember that we requested a replacement
        incidents.add(incident);
    }

    /**
     * Request that maintenance personnel inspect the Server installed
     * in a Rack to determine why it's health is low. Looks up the
     * unit of the Server (in the provided Rack) so we can file a
     * request in Wingnut. If a request to inspect the server
     * has already been made, does not repeat the request.
     *
     * @param rack The Rack the unhealthy Server is installed in.
     * @param server The unhealthy Server.
     * @throws RackMonitorException when out logic is incorrect.
     * @throws RackMonitorDependencyException When Wingnut fails.
     */
    public void arrangeInspection(Rack rack, Server server)
        throws RackMonitorException, RackMonitorDependencyException {

        // Get the unit slot of the Server, or throw NoSuchServerException
        int unit = getUnit(rack, server);

        // Check for duplicate requests.
        HealthIncident incident = new HealthIncident(server, rack, unit, INSPECT);
        if (incidents.contains(incident)) {
            logger.info("Already requested INSPECT for {} in {} unit: {}", server, rack, unit);
            return;
        }

        // Actually make the request
        try {
            logger.info("Requesting INSPECT for {} in {} unit: {}", server, rack, unit);
            wingnutClient.requestInspection(rack, unit);
        } catch (WingnutClientException e) {
            // Some problem in our logic; we passed a bad Rack or Unit
            logger.warn("Bad inputs for INSPECT {} in {} unit: {}", server, rack, unit, e);
            throw new RackMonitorException(e);
        } catch (WingnutServiceException e) {
            // Some problem with Wingnut
            logger.warn("Wingnut failed to INSPECT {} in {} unit: {}", server, rack, unit, e);
            throw new RackMonitorDependencyException(e);
        }

        // Remember the request
        incidents.add(incident);
    }

    /**
     * Helper method that finds the unit of a Server in a Rack, throwing a
     * service exception if there's a problem.
     * @param rack The Rack to find the Server in.
     * @param server The Server to find.
     * @return The unit of the Rack where the Server is attached.
     * @throws RackMonitorException if the Rack can't find the Server.
     *   This is a service exception because the RackMonitor should
     *   *get* the server from the rack; if the rack then can't find
     *   the server, something is wrong with our calling logic.
     */
    private int getUnit(Rack rack, Server server) throws RackMonitorException {
        int unit = 0;
        try {
            unit = rack.getUnitForServer(server);
        } catch (NoSuchServerException e) {
            // This shouldn't happen, since we retrieved the server
            // only milliseconds earlier
            String msg = String.format("Expected server %s in rack %s!", server, rack);
            logger.warn(msg);
            throw new RackMonitorException(msg, e);
        }
        return unit;
    }

}
