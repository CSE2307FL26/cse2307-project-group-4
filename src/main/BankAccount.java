package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Transaction> transactions;

    public BankAccount() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
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

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }
}
