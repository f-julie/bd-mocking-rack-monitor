package com.amazon.ata.mocking.wholefoodscookieassembly.exception;

/**
 * Thrown when an allergen is detected by the system. The entire assembly line needs to be shut down to be disinfected.
 */
public class AllergenContaminantException extends Exception {

    public AllergenContaminantException() {}

    public AllergenContaminantException(String message) {
        super(message);
    }

    public AllergenContaminantException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllergenContaminantException(Throwable cause) {
        super(cause);
    }
}
