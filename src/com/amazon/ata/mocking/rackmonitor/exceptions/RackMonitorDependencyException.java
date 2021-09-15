package com.amazon.ata.mocking.rackmonitor.exceptions;

/**
 * An exception for when one of the RackMonitor dependencies fails.
 */
public class RackMonitorDependencyException extends Exception {
    public RackMonitorDependencyException() {
        super();
    }

    public RackMonitorDependencyException(String message) {
        super(message);
    }

    public RackMonitorDependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RackMonitorDependencyException(Throwable cause) {
        super(cause);
    }
}
