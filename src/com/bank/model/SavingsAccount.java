 package com.bank.model;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(String accountId, String customerId, double initialBalance) {
        super(accountId, customerId, initialBalance, AccountType.SAVINGS);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}
