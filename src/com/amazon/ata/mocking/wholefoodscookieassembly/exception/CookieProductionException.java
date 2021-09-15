package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when an error occurs while packaging cookies in their boxes.
 */
public class CookieProductionException extends RuntimeException {
    public CookieProductionException() {}

    public CookieProductionException(String message) {
        super(message);
    }

    public CookieProductionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookieProductionException(Throwable cause) {
        super(cause);
    }
}
