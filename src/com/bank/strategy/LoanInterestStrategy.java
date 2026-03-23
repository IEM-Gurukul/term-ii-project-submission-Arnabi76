 package com.bank.strategy;

public class LoanInterestStrategy implements InterestStrategy {
    private static final double RATE = 0.12;

    @Override
    public double calculateInterest(double balance) {
        return balance * RATE;
    }
}
