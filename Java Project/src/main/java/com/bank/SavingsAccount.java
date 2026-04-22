package com.bank;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int customerId, double balance, double interestRate) {
        super(customerId, "Savings", balance);
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return balance * interestRate / 100;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
