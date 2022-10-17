package com.example.moneytransfersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Resource not found");
    }

}
