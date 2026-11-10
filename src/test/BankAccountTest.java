package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testSuccessfulTransfer() {
        BankAccount sourceAccount = new BankAccount();
        BankAccount destinationAccount = new BankAccount();
        
        sourceAccount.deposit(100);
        sourceAccount.transfer(40, destinationAccount);
        
        assertEquals(60, sourceAccount.getBalance(), 0.01);
        assertEquals(40, destinationAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferInvalidAmount() {
        BankAccount sourceAccount = new BankAccount();
        BankAccount destinationAccount = new BankAccount();
        sourceAccount.deposit(100);
        
        try {
            sourceAccount.transfer(-20, destinationAccount);
            fail("Should throw an exception for negative transfer amounts");
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testTransferInsufficientFunds() {
        BankAccount sourceAccount = new BankAccount();
        BankAccount destinationAccount = new BankAccount();
        sourceAccount.deposit(50);
        
        try {
            sourceAccount.transfer(100, destinationAccount);
            fail("Should throw an exception for transferring more than the balance");
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }
}
