package com.amazon.ata.mocking.subscriptions;

import com.amazon.ata.mocking.subscriptions.dependencies.EmailService;
import com.amazon.ata.mocking.subscriptions.resources.Customer;
import com.amazon.ata.mocking.subscriptions.resources.MonthlySubscription;
import com.amazon.ata.mocking.subscriptions.resources.Subscription;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailServiceClientTest {

    private CustomerDao customerDao;
    private EmailService emailService;

    private EmailServiceClient emailServiceClient;

    @BeforeEach
    public void setup() {
        customerDao = new CustomerDao();
        emailService = new EmailService();

        emailServiceClient = new EmailServiceClient(emailService, customerDao);
    }

    @Test
    public void sendNewSubscriptionEmail_emailAddressExists_returnTrue() {
        // GIVEN: valid subscription & customer data (with an email address)
        Subscription subscription = new MonthlySubscription("barnikki-B06XH36LTN", "B06XH36LTN",
            "barnikki");

        Customer customer = new Customer("barnikki", "barnikki@amazon.com", "Nikki",
            "Barry");

        customerDao.createCustomer(customer);

        // WHEN: you send a new Subscription email
        boolean emailSent = emailServiceClient.sendNewSubscriptionEmail(subscription);

        // THEN: Send the email and return true
        assertTrue(emailSent, "Expected the email to have successfully sent.");
    }
}
