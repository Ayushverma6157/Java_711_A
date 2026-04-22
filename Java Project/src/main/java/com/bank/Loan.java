package com.bank;

public class Loan {
    private int loanId;
    private int customerId;
    private double principal;
    private double annualRate;
    private int months;

    public Loan(int customerId, double principal, double annualRate, int months) {
        this.customerId = customerId;
        this.principal = principal;
        this.annualRate = annualRate;
        this.months = months;
    }

    public double calculateEMI() {
        double monthlyRate = annualRate / 12 / 100;
        if (monthlyRate == 0) {
            return principal / months;
        }
        return principal * monthlyRate * Math.pow(1 + monthlyRate, months)
                / (Math.pow(1 + monthlyRate, months) - 1);
    }

    public int getLoanId() {
        return loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public int getMonths() {
        return months;
    }
}
