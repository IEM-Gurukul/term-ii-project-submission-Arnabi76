package com.bank.model;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private String pin;

    public Customer(String customerId, String name, String email, String phone, String pin) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pin = pin;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPin() { return pin; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPin(String pin) { this.pin = pin; }

    @Override
    public String toString() {
        return customerId + "," + name + "," + email + "," + phone + "," + pin;
    }
}