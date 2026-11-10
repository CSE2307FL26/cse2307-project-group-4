package main;

public class BankAccount {

    private double balance;
    private boolean isClosed;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (this.isClosed) {
            throw new IllegalStateException("Cannot deposit into a closed account.");
        }
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void close() {
        this.isClosed = true;
    }

    public boolean isClosed() {
        return this.isClosed;
    }
}
