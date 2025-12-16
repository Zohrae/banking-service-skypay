package com.skypay.banking.exceptions;


 // Exception levée lorsqu'un retrait dépasse le solde disponible;
// comme si on veut retirer 1270 alors qu on a juste 1268 :/

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
