package com.amazon.ata.mocking.rackmonitor.clients.wingnut;

/**
 * Exception when the Wingnut service fails.
 */
public class WingnutServiceException extends Exception {
    public WingnutServiceException() {
        super();
    }

    public WingnutServiceException(String message) {
        super(message);
    }

    public WingnutServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WingnutServiceException(Throwable cause) {
        super(cause);
    }
}
