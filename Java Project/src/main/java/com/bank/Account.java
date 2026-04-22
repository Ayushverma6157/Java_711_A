package com.bank;

public class Account {
    protected int accountId;
    protected int customerId;
    protected String accountType;
    protected double balance;

    public Account() {
    }

    public Account(int customerId, String accountType, double balance) {
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            return;
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Not enough balance to withdraw.");
        }
        balance -= amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
