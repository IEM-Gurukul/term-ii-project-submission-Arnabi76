 package com.bank.strategy;

public class SavingsInterestStrategy implements InterestStrategy {
    private static final double RATE = 0.04;

    @Override
    public double calculateInterest(double balance) {
        return balance * RATE;
    }
}
