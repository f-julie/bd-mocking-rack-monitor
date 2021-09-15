package com.amazon.ata.mocking.subscriptions.exceptions;

/**
 * Thrown when an invalid input is provided to an API.
 *
 * For example, thrown if cancelSubscription() is called with a subscriptionId for a
 * non-existent subscription.
 */
public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 5786867834083962866L;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable e) {
        super(message, e);
    }
}
