package com.amazon.ata.mocking.rackmonitor.clients.wingnut;

/**
 * Exception when a client uses the Wingnut service incorrectly.
 */
public class WingnutClientException extends Exception {
    public WingnutClientException() {
        super();
    }

    public WingnutClientException(String message) {
        super(message);
    }

    public WingnutClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public WingnutClientException(Throwable cause) {
        super(cause);
    }
}
