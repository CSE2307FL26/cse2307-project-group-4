package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testAddInterest() {
        BankAccount account = new BankAccount();
        account.deposit(100);

        account.addInterest(2.50);

        assertEquals(102.50, account.getBalance(), 0.001);
    }

    @Test
    public void testAddInterestToZeroBalance() {
        BankAccount account = new BankAccount();

        account.addInterest(1.25);

        assertEquals(1.25, account.getBalance(), 0.001);
    }

    @Test
    public void testInvalidInterestAmounts() {
        BankAccount account = new BankAccount();
        account.deposit(100);

        double[] invalidAmounts = {
            0, -5, Double.NaN,
            Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY
        };

        for (double amount : invalidAmounts) {
            assertThrows(IllegalArgumentException.class,
                    () -> account.addInterest(amount));

            assertEquals(100, account.getBalance(), 0.001);
        }
    }
  
    @Test
    public void testCollectFee() {
        BankAccount account = new BankAccount();
        account.deposit(100);

        account.collectFee(12.50);

        assertEquals(87.50, account.getBalance(), 0.001);
    }

    @Test
    public void testCollectFeeEqualToBalance() {
        BankAccount account = new BankAccount();
        account.deposit(10);

        account.collectFee(10);

        assertEquals(0, account.getBalance(), 0.001);
    }

    @Test
    public void testFeeCannotExceedBalance() {
        BankAccount account = new BankAccount();
        account.deposit(10);

        assertThrows(IllegalArgumentException.class,
                () -> account.collectFee(10.01));

        assertEquals(10, account.getBalance(), 0.001);
    }

    @Test
    public void testInvalidFeeAmounts() {
        BankAccount account = new BankAccount();
        account.deposit(100);

        double[] invalidAmounts = {
            0, -5, Double.NaN,
            Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY
        };

        for (double amount : invalidAmounts) {
            assertThrows(IllegalArgumentException.class,
                    () -> account.collectFee(amount));

            assertEquals(100, account.getBalance(), 0.001);
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

    @Test
    public void testWithdrawFromClosedAccountThrowsException() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.close();
        
        try {
            // This assumes your teammate names the method "withdraw"
            testAccount.withdraw(50);
            fail("Should throw an exception when withdrawing from a closed account");
        } catch (IllegalStateException e) {
            // test passes
        }
    }

    @Test
    public void testTransferFromClosedAccount() {
        BankAccount sourceAccount = new BankAccount();
        BankAccount destinationAccount = new BankAccount();
        
        sourceAccount.deposit(100);
        sourceAccount.close();
        
        try {
            sourceAccount.transfer(50, destinationAccount);
            fail("Should throw an exception when transferring from a closed account");
        } catch (IllegalStateException e) {
            // test passes
        }
    }

    @Test
    public void testCollectFeeFromClosedAccountThrowsException() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.close();
        
        try {
            testAccount.collectFee(10);
            fail("Should throw an exception when collecting a fee from a closed account");
        } catch (IllegalStateException e) {
            // test passes
        }
    }

    @Test
    public void testAddInterestToClosedAccountThrowsException() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.close();
        
        try {
            testAccount.addInterest(5.0);
            fail("Should throw an exception when adding interest to a closed account");
        } catch (IllegalStateException e) {
            // test passes
        }
    }
}
