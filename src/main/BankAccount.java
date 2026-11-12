package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean isClosed;
    private List<Transaction> transactions;

    public BankAccount() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (this.isClosed) {
            throw new IllegalStateException("Cannot deposit into a closed account.");
        }
        if(amount > 0) {
            this.balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= this.balance) {
            this.balance -= amount;
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
  
    public void close() {
        this.isClosed = true;
    }

    public boolean isClosed() {
        return this.isClosed;
    }
  
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }
}
