 package com.bank.repository;

import com.bank.model.Customer;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer findById(String customerId) {
        return customers.get(customerId);
    }

    public boolean exists(String customerId) {
        return customers.containsKey(customerId);
    }

    public Map<String, Customer> getAll() {
        return customers;
    }

    public void delete(String customerId) {
        customers.remove(customerId);
    }
}
