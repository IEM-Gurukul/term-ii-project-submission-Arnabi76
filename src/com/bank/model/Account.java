 package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountId;
    protected String customerId;
    protected double balance;
    protected AccountType accountType;
    protected List<Transaction> transactionHistory;

    public Account(String accountId, String customerId, double initialBalance, AccountType accountType) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) throws Exception {
        if (amount <= 0) throw new Exception("Invalid amount");
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) throw new Exception("Invalid amount");
        if (amount > balance) throw new Exception("Insufficient funds");
        this.balance -= amount;
    }

    public abstract double calculateInterest();

    public String getAccountId() { return accountId; }
    public String getCustomerId() { return customerId; }
    public double getBalance() { return balance; }
    public AccountType getAccountType() { return accountType; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }

    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }

    @Override
    public String toString() {
        return accountId + "," + customerId + "," + balance + "," + accountType;
    }
}
