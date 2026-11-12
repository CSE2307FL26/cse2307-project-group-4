package main;
import java.util.ArrayList;
import java.util.List;

public class BankUser {
    private List<BankAccount> accounts;

    public BankUser() {
        accounts = new ArrayList<>();
        accounts.add(new BankAccount());
    }

    public BankAccount createAccount() {
        BankAccount newAccount = new BankAccount();
        accounts.add(newAccount);
        return newAccount;
    }

    public BankAccount getAccount(int index) {
        return accounts.get(index);
    }

    public int getAccountNumber() {
        return accounts.size();
    }
}
