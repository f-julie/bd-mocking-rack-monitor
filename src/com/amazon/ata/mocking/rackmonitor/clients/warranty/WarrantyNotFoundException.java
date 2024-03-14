package com.amazon.ata.mocking.rackmonitor.clients.warranty;

/**
 * Exception when a Server doesn't have a Warranty.
 * Every Server must have a Warranty, even if it's the nullWarranty.
 */
public class WarrantyNotFoundException extends Exception {
    public WarrantyNotFoundException() {
        super();
    }

    public WarrantyNotFoundException(String message) {
        super(message);
    }

    public WarrantyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarrantyNotFoundException(Throwable cause) {
        super(cause);
    }
}
