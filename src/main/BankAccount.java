package main;

public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void transfer(double amount, BankAccount destination) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        this.balance -= amount;
        destination.deposit(amount);
    }
}
