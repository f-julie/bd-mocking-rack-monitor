package com.amazon.ata.mocking.rackmonitor.exceptions;

/**
 * An exception for when we ask a Rack for a Server it doesn't contain.
 */
public class NoSuchServerException extends Exception {
    public NoSuchServerException() {
        super();
    }

    public NoSuchServerException(String message) {
        super(message);
    }

    public NoSuchServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchServerException(Throwable cause) {
        super(cause);
    }
}
