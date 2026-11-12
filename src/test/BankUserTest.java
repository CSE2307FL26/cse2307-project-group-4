package test;

import main.BankAccount;
import main.BankUser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BankUserTest {
@Test 
public void testCustomerStartsWithOneAccount() {
    BankUser user = new BankUser();
    assertEquals(1, user.getAccountNumber());
}

@Test 
public void testCreateNewAccounts() {
    BankUser user = new BankUser();
    user.createAccount();
    user.createAccount();
    assertEquals(3, user.getAccountNumber());
}

@Test 
public void  testAccountsAreSeperate() {
    BankUser user = new BankUser();

    BankAccount firstAccount = user.getAccount(0);
    BankAccount secondAccount = user.createAccount();

    firstAccount.deposit(100);

    assertEquals(100.0, firstAccount.getBalance(), 0.01);
    assertEquals(0.0, secondAccount.getBalance(), 0.01);
}
}
