package com.skypay.banking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * cette classe la represente une transaction bancaire (depot ou retrait)
 * contient la date, le montant et le solde apres l operation
 */

public class Transaction {

    private final LocalDate date;
    private final int amount;  // positif = depot, negatif = retrait
    private final int balance; // le solde apres cette transaction

    // format de date pour l affichage (ex: 11/09/2002)
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * constructeur de transaction
     * @param date - quand la transaction a eu lieu
     * @param amount - combien (positif pour depot, negatif pour retrait)
     * @param balance - le nouveau solde apr-s cette operation
     */

    public Transaction(LocalDate date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * formate la transaction pour laffichage dans le releve
     * format attendu : date || credit || debit || balance
     * exo : 11/09/2025 || || 500 || 2500
     */

    public String format() {
        // Si c"est un depot (positif), on l'affiche dans la colonne credit
        String credit = amount > 0 ? String.valueOf(amount) : "";

        // Si cest un retrait (negatif), on l affiche dans la colonne debit
        // Math.abs() pour enlever le signe moins
        String debit = amount < 0 ? String.valueOf(Math.abs(amount)) : "";

        // On assemble tout avec le format demande
        return String.format(
                "%s || %s || %s || %d",
                date.format(DATE_FORMAT),  // date formatee
                credit,                     // depot ou vide
                debit,                      // retrait ou vide
                balance                     // solde actuel
        );
    }
}