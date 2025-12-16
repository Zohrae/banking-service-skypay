package com.skypay.banking;

import com.skypay.banking.exceptions.InsufficientFundsException;
import com.skypay.banking.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests unitaires (partie par partie) pour la classe Account
 */

class AccountTest {

    private Account account;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        account = new Account();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("scénario d'acceptation complet")
    void acceptanceScenario() {
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        account.withdraw(500, LocalDate.of(2012, 1, 14));

        account.printStatement();

        String sep = System.lineSeparator(); // pour Windows/Linux/macOS
        String expected =
                "date || credit || debit || balance" + sep +
                        "14/01/2012 ||  || 500 || 2500" + sep +
                        "13/01/2012 || 2000 ||  || 3000" + sep +
                        "10/01/2012 || 1000 ||  || 1000" + sep;

        assertEquals(expected, outputStream.toString());
    }


    @Test
    void depositIncreasesBalance() {
        account.deposit(200, LocalDate.now());
        assertEquals(200, account.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        account.deposit(500, LocalDate.now());
        account.withdraw(200, LocalDate.now());
        assertEquals(300, account.getBalance());
    }

    @Test
    void withdrawingMoreThanBalanceThrowsException() {
        account.deposit(100, LocalDate.now());

        assertThrows(
                InsufficientFundsException.class,
                () -> account.withdraw(200, LocalDate.now())
        );
    }

    @Test
    void negativeDepositThrowsException() {
        assertThrows(
                InvalidAmountException.class,
                () -> account.deposit(-100, LocalDate.now())
        );
    }

    @Test
    void zeroDepositThrowsException() {
        assertThrows(
                InvalidAmountException.class,
                () -> account.deposit(0, LocalDate.now())
        );
    }

    @Test
    void emptyStatementPrintsOnlyHeader() {
        account.printStatement();

        String expected = "date || credit || debit || balance" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }
}
