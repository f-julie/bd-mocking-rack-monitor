package com.amazon.ata.mocking.rackmonitor;

import com.amazon.ata.mocking.rackmonitor.exceptions.NoSuchServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents a Rack in a data center.
 */
public class Rack {
    private final Map<Server, Integer> unitMap;
    private final String rackId;

    /**
     * Constructs a Rack with its ID and a map of
     * the Servers in its unit slots.
     * @param rackId The ID of this Rack.
     * @param unitMap A map of which unit slot each Server uses.
     */
    public Rack(String rackId, Map<Server, Integer> unitMap) {
        this.rackId = rackId;
        this.unitMap = unitMap;
    }

    /**
     * Returns the unit slot that the provided Server occupies.
     * @param server The Server to look up.
     * @return The top unit slot the Server occupies.
     * @throws NoSuchServerException If the provided Server isn't
     *         actually in this Rack.
     */
    public int getUnitForServer(Server server) throws NoSuchServerException {
        // Is the Server even in this Rack?
        if (!unitMap.containsKey(server)) {
            throw new NoSuchServerException();
        }
        return unitMap.get(server);
    }

    /**
     * Returns the ID of this Rack.
     * @return the ID of this Rack.
     */
    public String getRackId() {
        return this.rackId;
    }

    /**
     * Returns a Map of the health of each Server in this Rack,
     * based on our continuous monitoring of temperature, power
     * usage, time since installation, and other metrics.
     * @return A Map of the estimated health of each Server.
     */
    public Map<Server, Double> getHealth() {

        Map<Server, Double> serverHealthMap = new HashMap<>();
        for (Server server : unitMap.keySet()) {
            Double health = calculateTestHealth(server);
            if (health == null) {
                // A *real* Rack would calculate health based on
                // temperature, power usage, and so on.
                health = new Random().nextDouble();
            }
            serverHealthMap.put(server, health);
        }
        return serverHealthMap;
    }

    /**
     * Calculates a consistent health for a "test" server.
     * Ugh, why do I have to mix test data into my regular data?
     * Isn't there a better way to test?
     *
     * @param server A test Server to generate a fake health for.
     * @return The fake health; null if not a test server or
     *         the server ID isn't long enough.
     */
    private Double calculateTestHealth(Server server) {
        String serverId = server.getServerId();
        if (!serverId.startsWith("TEST") || serverId.length() < 8) {
            return null;
        }

        byte[] seedBytes = serverId.substring(4).getBytes();
        long seed = seedBytes[3] << 24 | seedBytes[2] << 16 |  seedBytes[1] << 8 | seedBytes[0];
        return new Random(seed).nextDouble();
    }

    @Override
    public String toString() {
        return String.format("rack: %s", rackId);
    }
}
