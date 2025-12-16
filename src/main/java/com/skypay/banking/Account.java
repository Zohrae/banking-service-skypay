package com.skypay.banking;

import com.skypay.banking.exceptions.InsufficientFundsException;
import com.skypay.banking.exceptions.InvalidAmountException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * represente un compte bancaire avec les opérations de base :
 * - dépot
 * - retrait
 * - impression du relevé
 */

public class Account {

    private int balance;
    private final List<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

     // depot d'argent sur le compte
    public void deposit(int amount, LocalDate date) {
        validateAmount(amount);

        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

     // retrait
    public void withdraw(int amount, LocalDate date) {
        validateAmount(amount);

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Solde insuffisant. Solde actuel: " + balance
            );
        }

        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }


    public void printStatement() {
        System.out.println("date || credit || debit || balance");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i).format());
        }
    }

    /**
     * validation du montant
     */
    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Le montant doit être strictement positif"
            );
        }
    }

     // solde courant (utile pour les tests)
    public int getBalance() {
        return balance;
    }


    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
