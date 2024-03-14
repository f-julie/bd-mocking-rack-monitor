package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when a cookie is too small to be suitable for sale. It can be used for cookie crumble.
 */
public class CookieTooSmallException extends CookieSizeException {
    public CookieTooSmallException() {}

    public CookieTooSmallException(String message) {
        super(message);
    }

    public CookieTooSmallException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookieTooSmallException(Throwable cause) {
        super(cause);
    }
}
