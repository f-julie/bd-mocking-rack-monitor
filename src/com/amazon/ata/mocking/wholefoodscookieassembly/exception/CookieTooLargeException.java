package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when a cookie is too large to be packaged in a box. It can be used for cookie crumble.
 */
public class CookieTooLargeException extends CookieSizeException {
    public CookieTooLargeException() {}

    public CookieTooLargeException(String message) {
        super(message);
    }

    public CookieTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookieTooLargeException(Throwable cause) {
        super(cause);
    }
}
