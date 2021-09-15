package com.amazon.ata.mocking.rackmonitor.clients.warranty;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a warranty for a single server.
 */
public class Warranty {
    private static final Warranty nullWarranty = new Warranty("nullWarranty");
    private final String warrantyId;
    // An actual Warranty would have a lot more details:
    // vendor, date, coverage, etc

    /**
     * Returns an instance of a Warranty with no terms. Used when we
     * specifically want to say that the server has no warranty.
     * @return an instance of a Warranty with no terms.
     */
    public static Warranty nullWarranty() {
        return nullWarranty;
    }

    public Warranty(String warrantyId) {
        this.warrantyId = warrantyId;
    }

    /**
     * Determines whether this Warranty has expired or not. The
     * "nullWarranty" is always expired.
     * @return true if the Warranty has expired, false otherwise.
     */
    public boolean hasExpired() {
        // The null warranty is always expired
        if (this == nullWarranty) {
            return true;
        }

        // "Check" the "expiration date"
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(warrantyId.getBytes());
            return digest[0] > 124;
        } catch (NoSuchAlgorithmException e) {
            // This should never happen, since all implementations of
            // Java must support SHA-1
            return false;
        }
    }

    public String getWarrantyId() {
        return this.warrantyId;
    }

    @Override
    public String toString() {
        if (this == this.nullWarranty) {
            return "nullWarranty";
        }
        return String.format("Warranty: %s", warrantyId);
    }
}
