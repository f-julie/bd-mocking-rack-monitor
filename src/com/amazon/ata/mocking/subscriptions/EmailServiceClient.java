package com.amazon.ata.mocking.subscriptions;

import com.amazon.ata.mocking.subscriptions.dependencies.EmailService;
import com.amazon.ata.mocking.subscriptions.resources.Customer;
import com.amazon.ata.mocking.subscriptions.resources.Subscription;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sends subscription related emails via the EmailService
 */
public class EmailServiceClient {
    private EmailService emailService;
    private CustomerDao customerDao;
    private Logger log = LogManager.getLogger(EmailService.class);

    /**
     * Constructs a new EmailServiceClient
     *
     * @param emailService - the Email Service used to send emails
     * @param customerDao - our data store for customer data
     */
    public EmailServiceClient(EmailService emailService, CustomerDao customerDao) {
        this.emailService = emailService;
        this.customerDao = customerDao;
    }

    /**
     * Sends an email to a customer notifying them that they have a new subscription.
     *
     * @param subscription The new subscription for which to send our notification email.
     * @return true if the email was able to be sent, false otherwise.
     */
    public boolean sendNewSubscriptionEmail(Subscription subscription) {
        log.info("Sending new subscription email for subscription {}", subscription);

        Customer customer = customerDao.getCustomer(subscription.getCustomerId());
        String emailAddress = customer.getEmailAddress();
        if (emailAddress != null) {
            String message = String.format("Dear %s, you have a new subscription %s for item %s",
                customer.getFullName(), subscription.getSubscriptionId(), subscription.getAsin());

            log.info("Sending email {} to {}", message, emailAddress);
            emailService.sendEmail(emailAddress, message);
            return true;
        }

        return false;
    }

    /**
     * Sends an email to a customer notifying them that their subscription has been cancelled.
     *
     * @param subscription The cancelled subscription for which to send our notification email.
     * @return true if the email was able to be sent, false otherwise.
     */
    public boolean sendCancelledSubscriptionEmail(Subscription subscription) {
        log.info("Sending cancellation email for subscription {}", subscription);

        Customer customer = customerDao.getCustomer(subscription.getCustomerId());
        String emailAddress = customer.getEmailAddress();
        if (emailAddress != null) {
            String message = String.format("Dear %s, your subscription %s has been cancelled",
                customer.getFullName(), subscription.getSubscriptionId());

            log.info("Sending email {} to {}", message, emailAddress);
            emailService.sendEmail(customer.getEmailAddress(), message);
            return true;
        }

        return false;
    }
}
