package com.amazon.ata.mocking.subscriptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionLibraryTest {

    @BeforeEach
    public void setup() {

    }

    @Test
    public void addMonthlySubscription_newSubscription_sendsNewSubscriptionEmail() {
        assertTrue(false);
    }

    @Test
    public void cancelSubscription_subscriptionDoesNotExist_throwsInvalidInputException() {
        assertTrue(false);
    }
}
