 package com.bank.model;

public class CurrentAccount extends Account {
    private static final double INTEREST_RATE = 0.02;

    public CurrentAccount(String accountId, String customerId, double initialBalance) {
        super(accountId, customerId, initialBalance, AccountType.CURRENT);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}
