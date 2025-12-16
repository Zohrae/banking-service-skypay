package com.skypay.banking.exceptions;

    // exception sera levée lorsqu'un montant est invalide;
public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {
        super(message);
    }
}
