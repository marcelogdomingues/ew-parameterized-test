package nl.engineers.week.bank;

import nl.engineers.week.parameterizedtest.bank.BankAccountExample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountExampleTest {

    @ParameterizedTest
    @DisplayName("Test deposit with valid amounts")
    @CsvSource({
            "100.0, 50.0, 150.0",
            "200.0, 100.0, 300.0",
            "0.0, 25.0, 25.0"
    })
    void testDeposit(double initialBalance, double depositAmount, double expectedBalance) {
        BankAccountExample account = new BankAccountExample("12345", initialBalance);
        account.deposit(depositAmount);
        assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest
    @DisplayName("Test withdraw with valid amounts")
    @CsvSource({
            "100.0, 50.0, 50.0",
            "200.0, 100.0, 100.0",
            "50.0, 25.0, 25.0"
    })
    void testWithdraw(double initialBalance, double withdrawAmount, double expectedBalance) {
        BankAccountExample account = new BankAccountExample("12345", initialBalance);
        account.withdraw(withdrawAmount);
        assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest
    @DisplayName("Test withdraw with insufficient funds")
    @CsvSource({
            "50.0, 100.0",
            "0.0, 10.0",
            "25.0, 50.0"
    })
    void testWithdrawInsufficientFunds(double initialBalance, double withdrawAmount) {
        BankAccountExample account = new BankAccountExample("12345", initialBalance);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(withdrawAmount));
    }

    @ParameterizedTest
    @DisplayName("Test deposit with invalid amounts")
    @CsvSource({
            "100.0, -50.0",
            "200.0, 0.0"
    })
    void testDepositInvalidAmounts(double initialBalance, double depositAmount) {
        BankAccountExample account = new BankAccountExample("12345", initialBalance);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(depositAmount));
    }

    @Test
    @DisplayName("Test account creation with negative initial balance")
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccountExample("12345", -100.0));
    }
}