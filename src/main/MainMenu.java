package main;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 10;
	  private static final int MAX_SELECTION = 10;
    
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
        System.out.println("6. Close an existing account"); 
        System.out.println("7. Transfer money");
        System.out.println("8. Collect an account fee (administrator)");
        System.out.println("9. Add an interest payment (administrator)");
        System.out.println("10. Exit the app");

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
            case 6:
                performCloseAccount();
                break;
            case 7:
                performTransfer();
                break;
            case 8:
                performFeeCollection();
                break;
            case 9:
                performInterestPayment();
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

    public void performInterestPayment() {
        System.out.print("Enter the interest payment amount: ");
    }
  
    public void performFeeCollection() {
        System.out.print("Enter the fee amount: ");

        if (!keyboardInput.hasNextDouble()) {
            keyboardInput.next();
            System.out.println("Please enter a number.");
            return;
        }

        double amount = keyboardInput.nextDouble();

        try {
            userAccount.collectFee(amount);
            System.out.printf("Fee collected. Balance: $%.2f%n",
                    userAccount.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
    }
      
    public void performInterestPayment() {
        System.out.print("Enter the interest payment amount: ");

        if (!keyboardInput.hasNextDouble()) {
            keyboardInput.next();
            System.out.println("Please enter a number.");
            return;
        }

        double amount = keyboardInput.nextDouble();

        try {
            userAccount.addInterest(amount);
            System.out.printf("Interest added. Balance: $%.2f%n",
                    userAccount.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
      
    public void performTransfer() {
        double transferAmount = -1;
        while(transferAmount < 0) {
            System.out.print("How much would you like to transfer: ");
            transferAmount = keyboardInput.nextInt();
        }
        // TODO: Once the "Create additional account" feature is merged, 
        // replace this temporary account with a lookup for a valid existing account.
        BankAccount destinationAccount = new BankAccount();
        try {
            userAccount.transfer(transferAmount, destinationAccount);
            System.out.println("Transfer successful. Your new balance: $" + userAccount.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
    }
      
    public void performCloseAccount() {
        userAccount.close();
        System.out.println("Account successfully closed.");
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