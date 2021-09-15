package com.amazon.ata.mocking.subscriptions.dependencies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service to send emails (Pretend this is an external service)
 */
public class EmailService {
    Logger log = LogManager.getLogger(EmailService.class);

    /**
     * Sends an email message to an email address
     *
     * @param emailAddress the email address to which the email should be sent
     * @param emailMessage the message which should be sent via email
     */
    public void sendEmail(String emailAddress, String emailMessage) {
        String message = String.format("Sending email %s to %s", emailMessage, emailAddress);
        log.debug(message);

        // Pretend this is sending the email instead of printing to console.
        System.out.println(message);
    }
}
