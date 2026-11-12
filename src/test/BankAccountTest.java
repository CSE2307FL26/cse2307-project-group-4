package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

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
            // do nothing, test passes
        }
    }

    @Test
    public void testInitialBalance() {
        BankAccount testAccount = new BankAccount();
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testMultipleDeposits() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        testAccount.deposit(25);
        assertEquals(75, testAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);

        testAccount.withdraw(50);

        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test 
    public void testInvalidWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);

        try {
            testAccount.withdraw(-50);
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
  
    @Test 
    public void testOverdraft() {   
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);

        try {
            testAccount.withdraw(150);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test 
    public void testCloseAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.close();
        assertTrue("Account should be marked as closed", testAccount.isClosed());
    }

    @Test
    public void testDepositOnClosedAccountThrowsException() {
        BankAccount testAccount = new BankAccount();
        testAccount.close();
        try {
            testAccount.deposit(50);
            fail("Should throw an exception when depositing into a closed account");
        } catch (IllegalStateException e) {
            // do nothing, test passes
        }
    }

    // TODO: test after finish withdraw()
    // @Test
    // public void testWithdrawFromClosedAccountThrowsException() {
    //     BankAccount testAccount = new BankAccount();
    //     testAccount.deposit(100);
    //     testAccount.close();
        
    //     try {
    //         // This assumes your teammate names the method "withdraw"
    //         testAccount.withdraw(50);
    //         fail("Should throw an exception when withdrawing from a closed account");
    //     } catch (IllegalStateException e) {
    //         // test passes
    //     }
    // }

    // TODO: test after finish transfer()
    // @Test
    // public void testTransferFromClosedAccount() {
    //     BankAccount sourceAccount = new BankAccount();
    //     BankAccount destinationAccount = new BankAccount();
        
    //     sourceAccount.deposit(100);
    //     sourceAccount.close();
        
    //     try {
    //         sourceAccount.transfer(50, destinationAccount);
    //         fail("Should throw an exception when transferring from a closed account");
    //     } catch (IllegalStateException e) {
    //         // test passes
    //     }
    // }
}
