package com.amazon.ata.mocking.subscriptions.resources;

/**
 * Represents a subscription. Each subscription's id must be unique.
 */
public interface Subscription {
    public String getAsin();
    public String getCustomerId();
    public String getSubscriptionId();
}
