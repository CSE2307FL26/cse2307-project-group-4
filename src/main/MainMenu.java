package main;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 6;
    private static final int MAX_SELECTION = 6;

    private BankUser user;
    private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.user = new BankUser();
        this.userAccount = user.getAccount(0);
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");

        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Check account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Create an additional account");
        System.out.println("6. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                displayBalance();
                break;
            case 4:
                viewTransactionHistory();
                break;
            case 5:
                performCreateAccount();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while (depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void performCreateAccount() {
        user.createAccount();

        System.out.println("Account created successfully!");
        System.out.println("You now have "
                + user.getAccountNumber() + " accounts.");
    }

    public void viewTransactionHistory() {
        List<Transaction> history = userAccount.getTransactionHistory();

        System.out.println("Transaction History:");

        if (history.isEmpty()) {
            System.out.println("No transactions.");
            return;
        }

        for (Transaction transaction : history) {
            System.out.println(transaction);
        }
    }

    private BankAccount selectAccount() {
        System.out.println("Choose an account from 1 to " + user.getAccountNumber());

        int selection = getUserSelection(user.getAccountNumber());
        return user.getAccount(selection - 1);
    }

    public void displayBalance() {
        BankAccount selectedAccount = selectAccount();

        System.out.println("Current balance: " + selectedAccount.getBalance());
    }

    public void performWithdrawal() {
        double withdrawalAmount = -1;

        while (withdrawalAmount <= 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawalAmount = keyboardInput.nextDouble();
        }

        try {
            userAccount.withdraw(withdrawalAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal must be positive and cannot exceed the current balance.");
        }
    }

    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

}