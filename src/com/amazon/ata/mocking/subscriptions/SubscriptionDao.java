package com.amazon.ata.mocking.subscriptions;

import com.amazon.ata.mocking.subscriptions.resources.Subscription;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * A Data Access Object (DAO) for Subscription
 * Exposes CRUD (Create, Retrieve, Update, Delete) methods for subscriptions
 *
 * This DAO stores the relationship between a unique subscriptionId (String) and related subscription data (Subscription)
 */
public class SubscriptionDao {
    private Map<String, Subscription> subscriptions;
    private Logger log = LogManager.getLogger(SubscriptionLibrary.class);


    public SubscriptionDao() {
        subscriptions = new HashMap<>();
    }

    /**
     * Stores a subscription's data in our data store.
     *
     * @param subscription the subscription to be stored in our data store
     * @return true if the subscription did not already exist in our data store, false otherwise
     */
    public boolean createSubscription(Subscription subscription) {
        log.info("Creating subscription {}", subscription);
        return updateSubscription(subscription) == null;
    }

    /**
     * Retrieves  subscription data from our data store via it's subscriptionId.
     *
     * @param subscriptionId the id for the subscription data desired.
     * @return null if the subscription does not exist in our data store, otherwise the Subscription matching
     * the subscriptionId in our data store
     */
    public Subscription getSubscription(String subscriptionId) {
        log.info("Getting subscription {}", subscriptionId);
        return subscriptions.get(subscriptionId);
    }

    /**
     * Updates a subscriptions's data in our data store.
     *
     * @param subscription the updated subscription data to save in our data store.
     * @return null if the subscription did not previously exist in our data store, otherwise the previous
     * Subscription data stored under the subscription's id.
     */
    public Subscription updateSubscription(Subscription subscription) {
        log.info("Updating subscription {}", subscription);
        return subscriptions.put(subscription.getSubscriptionId(), subscription);
    }

    /**
     * Removes a subscription's data from our data store.
     *
     * @param subscriptionId the id of the subscription to remove from our data store.
     * @return null if the subscription did not previously exist in our data store, otherwise the previous
     * Subscription data stored under the given subscriptionId.
     */
    public Subscription deleteSubscription(String subscriptionId) {
        log.info("Deleting subscription {}", subscriptionId);
        return subscriptions.remove(subscriptionId);
    }
}
