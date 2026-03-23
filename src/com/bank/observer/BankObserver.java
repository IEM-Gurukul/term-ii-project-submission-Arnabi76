 package com.bank.observer;

public interface BankObserver {
    void update(String accountId, double amount, String transactionType);
}
