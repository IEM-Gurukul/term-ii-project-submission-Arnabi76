 package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String accountId;
    private String type; // DEPOSIT, WITHDRAW, TRANSFER
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(String transactionId, String accountId, String type, double amount, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }

    public String getTransactionId() { return transactionId; }
    public String getAccountId() { return accountId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return transactionId + "," + accountId + "," + type + "," + amount + "," + timestamp + "," + description;
    }
}
