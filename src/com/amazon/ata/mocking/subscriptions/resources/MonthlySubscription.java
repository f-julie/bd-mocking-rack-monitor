package com.amazon.ata.mocking.subscriptions.resources;

import java.util.Objects;

/**
 * Represents an monthly subscription. Each subscription's id must be unique.
 */
public class MonthlySubscription implements Subscription {
    private String subscriptionId;
    private String asin;
    private String customerId;

    /**
     * Constructs a monthly subscription for a given item and customer.
     *
     * @param subscriptionId The id representing the subscription (must be unique)
     * @param asin The asin representing the item subscribed to (cannot be null)
     * @param customerId The id of the customer who ordered the subscription (cannot be null)
     */
    public MonthlySubscription(String subscriptionId, String asin, String customerId) {
        this.subscriptionId = subscriptionId;
        this.asin = asin;
        this.customerId = customerId;
    }

    @Override
    public String getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public String getAsin() {
        return asin;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        MonthlySubscription other = (MonthlySubscription) obj;

        return Objects.equals(subscriptionId, other.subscriptionId) &&
            Objects.equals(asin, other.asin) &&
            Objects.equals(customerId, other.customerId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(subscriptionId, asin, customerId);
    }

    @Override
    public String toString() {
        return String.format("SubscriptionId: %s, asin: %s, customerId: %s" + subscriptionId, asin, customerId);
    }
}
