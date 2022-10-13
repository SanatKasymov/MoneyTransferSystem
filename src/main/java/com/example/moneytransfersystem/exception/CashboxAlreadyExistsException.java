package com.example.moneytransfersystem.exception;

public class CashboxAlreadyExistsException extends RuntimeException {

    public CashboxAlreadyExistsException(String name) {
        super(String.format("Cashbox with name %s already exists", name));
    }

}
