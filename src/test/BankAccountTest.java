package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
}
