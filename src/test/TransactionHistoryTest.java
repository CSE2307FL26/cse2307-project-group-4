package test;

import main.BankAccount;
import main.Transaction;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransactionHistoryTest {
    
    @Test 
    public void testDepositTransaction() {
        BankAccount account = new BankAccount();
        account.deposit(50);

        List<Transaction> history = account.getTransactionHistory();

        assertEquals(1, history.size());
        assertEquals("Deposit", history.get(0).getType());
        assertEquals(50.0, history.get(0).getAmount(), 0.01);
        assertNotNull(history.get(0).getTimestamp());
    }

    @Test
    public void testMultipleDeposits() {
        BankAccount account = new BankAccount();

        account.deposit(200);
        account.deposit(50);
        List<Transaction> history = account.getTransactionHistory();

        assertEquals(2, history.size());
        assertEquals(200.0, history.get(0).getAmount(), 0.01);
        assertEquals(50.0, history.get(1).getAmount(), 0.01);
    }

    @Test
    public void testAccountsHaveSeperateHistory() {
        BankAccount AccountOne = new BankAccount();
        BankAccount AccountTwo = new BankAccount();

        AccountOne.deposit(25);

        assertEquals(1, AccountOne.getTransactionHistory().size());
        assertTrue(AccountTwo.getTransactionHistory().isEmpty());
    }
}
