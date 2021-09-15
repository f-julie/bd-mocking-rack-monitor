package com.amazon.ata.mocking.rackmonitor.exceptions;

/**
 * An exception for when RackMonitor fails.
 */
public class RackMonitorException extends Exception {
    public RackMonitorException() {
        super();
    }

    public RackMonitorException(String message) {
        super(message);
    }

    public RackMonitorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RackMonitorException(Throwable cause) {
        super(cause);
    }
}
