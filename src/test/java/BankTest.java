import com.globant.Entities.Bank;
import com.globant.Entities.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BankTest {
    private Bank bank;
    private UserAccount user1;
    private UserAccount user2;

    @BeforeEach
    void setUp() {
        bank = new Bank("admin", "adminPassword");
        user1 = new UserAccount("user1", "user1Password");
        user2 = new UserAccount("user2", "user2Password");
        bank.addUserAccount(user1);
        bank.addUserAccount(user2);
    }

    @Test
    void testGetUserAccount() {
        UserAccount result = bank.getUserAccount("user1");
        assertEquals(user1, result);
    }

    @Test
    void testGetUserAccountNotFound() {
        UserAccount result = bank.getUserAccount("user3");
        assertNull(result);
    }

    @Test
    void testTransfer() {
        user1.deposit(100.0);
        bank.transfer(user1, user2, 50.0);
        assertEquals(50.0, user1.getBalance(), 0.001);
        assertEquals(50.0, user2.getBalance(), 0.001);
    }

    @Test
    void testTransferInsufficientFunds() {
        user1.deposit(50.0);
        bank.transfer(user1, user2, 100.0);
        assertEquals(50.0, user1.getBalance(), 0.001);
        assertEquals(0.0, user2.getBalance(), 0.001);
    }
}
