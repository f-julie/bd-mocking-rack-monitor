package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when a cookie is too gross, a.k.a. a raisin was detected. It may be a runtime surprise to the customer that
 * this isn't a chocolate chip cookie.
 */
public class GrossCookieException extends CookieTasteException {
    public GrossCookieException() {}

    public GrossCookieException(String message) {
        super(message);
    }

    public GrossCookieException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrossCookieException(Throwable cause) {
        super(cause);
    }
}
