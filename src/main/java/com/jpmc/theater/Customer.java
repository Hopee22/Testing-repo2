package com.jpmc.theater;

import java.util.Objects;

/*
Changed by: kaviya kanakaraj

-> updated the variable names
 */
public class Customer {

    private String customerName;

    private String customerId;

    /**
     * @param customerName customer name
     * @param customerId customer id
     */
    public Customer(String customerName, String customerId) {
        this.customerId = customerId; // NOTE - id is not used anywhere at the moment

        this.customerName = customerName;

        }

    public String getName() {
        return customerName;
    }

    public String getId(){
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName) && Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, customerId);
    }

    @Override
    public String toString() {
        return "name: " + customerName;
    }
}