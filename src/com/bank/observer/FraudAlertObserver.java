 package com.bank.observer;

public class FraudAlertObserver implements BankObserver {
    private static final double THRESHOLD = 50000;

    @Override
    public void update(String accountId, double amount, String transactionType) {
        if (amount > THRESHOLD) {
            System.out.println("🚨 FRAUD ALERT: Large transaction detected!");
            System.out.println("   Account: " + accountId);
            System.out.println("   Amount: Rs." + amount);
            System.out.println("   Type: " + transactionType);
        }
    }
}
