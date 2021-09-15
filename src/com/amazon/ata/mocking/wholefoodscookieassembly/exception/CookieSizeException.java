package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when a cookie is not the right size to be packaged. It can be used for cookie crumble.
 */
public class CookieSizeException extends Exception {
    public CookieSizeException() {}

    public CookieSizeException(String message) {
        super(message);
    }

    public CookieSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookieSizeException(Throwable cause) {
        super(cause);
    }
}
