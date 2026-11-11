package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
}
