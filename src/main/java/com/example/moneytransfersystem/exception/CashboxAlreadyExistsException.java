package com.example.moneytransfersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CashboxAlreadyExistsException extends BaseException {

    public CashboxAlreadyExistsException(String name) {
        super(HttpStatus.CONFLICT, String.format("Cashbox with name %s already exists", name));
    }

}
