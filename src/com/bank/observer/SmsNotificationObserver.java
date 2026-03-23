 package com.bank.observer;

public class SmsNotificationObserver implements BankObserver {
    @Override
    public void update(String accountId, double amount, String transactionType) {
        System.out.println("📱 SMS Sent: Transaction of Rs." + amount +
                " (" + transactionType + ") on account " + accountId);
    }
}
