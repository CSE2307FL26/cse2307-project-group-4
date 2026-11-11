package main;

public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void collectFee(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Fee must be a positive, finite amount.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient funds to collect this fee.");
        }

        balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }
}
