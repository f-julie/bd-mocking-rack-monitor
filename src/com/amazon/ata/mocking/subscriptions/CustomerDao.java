package com.amazon.ata.mocking.subscriptions;

import com.amazon.ata.mocking.subscriptions.resources.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * A Data Access Object (DAO) for Customer
 * Exposes CRUD (Create, Retrieve, Update, Delete) methods for customers
 *
 * This DAO stores the relationship between a unique customerId (String) and a customer's data (Customer)
*/
public class CustomerDao {
    private Map<String, Customer> customers;

    public CustomerDao() {
        customers = new HashMap<>();
    }

    /**
     * Stores the customer's data in our data store.
     *
     * @param customer the customer to be stored in our data store
     * @return true if the customer did not already exist in our data store, false otherwise
     */
    public boolean createCustomer(Customer customer) {
        return updateCustomer(customer) == null;
    }

    /**
     * Retrieves the customer data from our data store via it's customerId.
     *
     * @param customerId the id for the customer information desired.
     * @return null if the customer does not exist in our data store, otherwise the Customer matching
     * the customerId in our data store
     */
    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }

    /**
     * Updates a customer's data in our data store.
     *
     * @param customer the updated customer data to save in our data store.
     * @return null if the customer did not previously exist in our data store, otherwise the previous
     * Customer data stored under the customer's id.
     */
    public Customer updateCustomer(Customer customer) {
        return customers.put(customer.getCustomerId(), customer);
    }

    /**
     * Removes a customer's data from our data store.
     *
     * @param customerId the id of the customer to remove from our data store.
     * @return null if the customer did not previously exist in our data store, otherwise the previous
     * Customer data stored under the given customerId.
     */
    public Customer deleteCustomer(String customerId) {
        return customers.remove(customerId);
    }
}
