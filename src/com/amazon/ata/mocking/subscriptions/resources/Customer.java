package com.amazon.ata.mocking.subscriptions.resources;

import java.util.Objects;

/**
 * Represents an amazon customer. Each customer's id must be unique
 */
public class Customer {
    private String customerId;
    private String emailAddress;
    private String firstName;
    private String lastName;

    /**
     * Constructs a customer
     *
     * @param customerId The id representing the customer (must be unique)
     * @param emailAddress The customer's email address (can be null)
     * @param firstName The customer's first name (cannot be null)
     * @param lastName The customer's last name (cannot be null)
     */
    public Customer(String customerId, String emailAddress, String firstName, String lastName) {
        this.customerId = customerId;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Customer customer = (Customer) obj;

        return Objects.equals(customerId, customer.customerId) &&
            Objects.equals(emailAddress, customer.emailAddress) &&
            Objects.equals(firstName, customer.firstName) &&
            Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, emailAddress, firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("CustomerId: %s, Email: %s, First Name: %s, Last Name : %s",
            customerId, emailAddress, firstName, lastName);
    }
}
