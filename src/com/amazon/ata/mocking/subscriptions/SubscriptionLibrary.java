package com.amazon.ata.mocking.subscriptions;

import com.amazon.ata.mocking.subscriptions.exceptions.InvalidInputException;
import com.amazon.ata.mocking.subscriptions.resources.MonthlySubscription;
import com.amazon.ata.mocking.subscriptions.resources.Subscription;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;


/**
 * Manages and manipulates a customer's collection of subscriptions.
 */
public class SubscriptionLibrary {
    private SubscriptionDao subscriptionDao;
    private EmailServiceClient emailServiceClient;

    private Logger log = LogManager.getLogger(SubscriptionLibrary.class);

    public SubscriptionLibrary(SubscriptionDao subscriptionDao, EmailServiceClient emailServiceClient) {
        this.subscriptionDao = subscriptionDao;
        this.emailServiceClient = emailServiceClient;
    }

    /**
     * Cancels a given subscription. If the subscription is successfully cancelled, sends an email
     * notifying the customer who ordered the subscription that the subscription has been cancelled.
     *
     * @param subscriptionId the subscriptionId of the subscription to be cancelled.
     */
    public void cancelSubscription(String subscriptionId) throws InvalidInputException {
        Subscription subscription = subscriptionDao.deleteSubscription(subscriptionId);

        if (Objects.isNull(subscription)) {
            String errorMessage = String.format("Subscription %s does not exist.", subscriptionId);

            log.error(errorMessage);
            throw new InvalidInputException(errorMessage);
        }

        emailServiceClient.sendCancelledSubscriptionEmail(subscription);
    }

    /**
     * Creates a new monthly subscription for a given asin and customer. If creating the subscription succeeds,
     * this will send an email to the customer that they have a new subscription.
     *
     * @param asin the asin to which the customer is subscribing.
     * @param customerId the id of the customer placing the subscription order.
     * @return subscriptionId the subscriptionId for the newly created subscription.
     */
    public String addMonthlySubscription(String asin, String customerId) throws InvalidInputException {
        log.info("addMonthlySubscription called for asin: {}, customer: {}", asin, customerId);

        String subscriptionId = String.format("%s-%s", customerId, asin);

        Subscription subscription = new MonthlySubscription(subscriptionId, asin, customerId);

        boolean isNewSubscription = subscriptionDao.createSubscription(subscription);
        if (!isNewSubscription) {
            String errorMessage = String.format("Cannot add subscription %s, as it already exists.", subscriptionId);

            log.error(errorMessage);
            throw new InvalidInputException(errorMessage);
        }

        emailServiceClient.sendNewSubscriptionEmail(subscription);

        return subscription.getSubscriptionId();
    }
}
