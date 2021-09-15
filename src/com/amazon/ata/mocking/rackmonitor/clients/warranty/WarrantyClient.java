package com.amazon.ata.mocking.rackmonitor.clients.warranty;

import com.amazon.ata.mocking.rackmonitor.Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a connection to a remote service that returns
 * Warranty details.
 */
public class WarrantyClient {

    /**
     * Looks up the Warranty for the provided Server.
     * @param server The Server to look up the Warranty for.
     * @return the Warranty for the given server.
     * @throws WarrantyNotFoundException if the Server has no Warranty.
     *     If a server explicitly is not warrantied,
     *     the nullWarranty will be returned.
     */
    public Warranty getWarrantyForServer(Server server) throws WarrantyNotFoundException {

        String serverId = server.getServerId();

        Warranty warranty;
        // It's a shame I have to keep test data mixed in with my
        // valid production data just to support services that
        // don't know how to mock.
        warranty = calculateTestWarranty(serverId);
        if (warranty == null) {

            // A real service would look up the warranty here
            warranty = new Warranty(String.format("for %s", serverId));
        }
        return warranty;
    }

    /**
     * Ugh, now I've got to handle all the warranty cases so
     * my dependents can thoroughly test.
     * @param serverId The serverId to generate a test Warranty for.
     * @return a Warranty corresponding to the test Server.
     */
    private Warranty calculateTestWarranty(String serverId) throws WarrantyNotFoundException {
        if (!serverId.startsWith("TEST")) {
            return null;
        }

        try {
            byte[] digest = MessageDigest.getInstance("SHA-1")
                .digest(serverId.getBytes());
            if (digest[10] > 120) {
                String msg = String.format("Server %s has no warranty!", serverId);
                throw new WarrantyNotFoundException(msg);
            }
        } catch (NoSuchAlgorithmException e) {
            // This should never happen, since all implementations of
            // Java must support SHA-1
            throw new WarrantyNotFoundException(e);
        }

        return new Warranty(String.format("fake warranty for %s", serverId));
    }
}
