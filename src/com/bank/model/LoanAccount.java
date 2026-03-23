 package com.bank.model;

public class LoanAccount extends Account {
    private static final double INTEREST_RATE = 0.12;
    private double loanAmount;
    private double remainingBalance;

    public LoanAccount(String accountId, String customerId, double loanAmount) {
        super(accountId, customerId, 0, AccountType.LOAN);
        this.loanAmount = loanAmount;
        this.remainingBalance = loanAmount;
    }

    @Override
    public double calculateInterest() {
        return remainingBalance * INTEREST_RATE;
    }

    public double getLoanAmount() { return loanAmount; }
    public double getRemainingBalance() { return remainingBalance; }

    public void repay(double amount) throws Exception {
        if (amount <= 0) throw new Exception("Invalid amount");
        if (amount > remainingBalance) throw new Exception("Amount exceeds remaining balance");
        remainingBalance -= amount;
    }

    public boolean isFullyRepaid() {
        return remainingBalance == 0;
    }
}
