package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown if a cookie has less than 5 chocolate chips or more than 20 chocolate chips.
 */
public class InvalidNumberOfChipsException extends CookieTasteException {
    public InvalidNumberOfChipsException() {}

    public InvalidNumberOfChipsException(String message) {
        super(message);
    }

    public InvalidNumberOfChipsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfChipsException(Throwable cause) {
        super(cause);
    }
}
