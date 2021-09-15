package com.amazon.ata.mocking.rackmonitor.clients.wingnut;

import com.amazon.ata.mocking.rackmonitor.Rack;
import com.amazon.ata.mocking.rackmonitor.clients.warranty.Warranty;

/**
 * Represents a service that handles hardware in a data center.
 */
public class WingnutClient {

    /**
     * Notifies Maintenance that a server is unhealthy and should be
     * replaced, referencing whatever Warranty may apply.
     * @param rack The Rack containing the unhealthy server.
     * @param unit The unit slot where the server is installed.
     * @param warranty The Warranty that applies to the server.
     * @throws WingnutClientException if the inputs are invalid.
     * @throws WingnutServiceException if something goes wrong.
     */
    public void requestReplacement(Rack rack, int unit, Warranty warranty)
        throws WingnutClientException, WingnutServiceException {

        if (warranty == null) {
            throw new WingnutClientException("Warranty must not be null!");
        }

        // A real service would create a work order, and might thrown an exception.
        System.out.println(String.format("Replacement requested for %s unit %d under %s",
            rack, unit, warranty));
    }

    /**
     * Notifies Maintenance that a server is not guaranteed to be healthy,
     * and someone should inspect the installation.
     * @param rack The rack containing the suspect server.
     * @param unit The top unit slot where the server is installed.
     * @throws WingnutClientException if the inputs are invalid.
     * @throws WingnutServiceException if something goes wrong.
     */
    public void requestInspection(Rack rack, int unit)
        throws WingnutClientException, WingnutServiceException {

        // A real service would create a work order, and might thrown an exception.
        System.out.println(String.format("Inspection requested for %s unit %d", rack, unit));
    }
}
