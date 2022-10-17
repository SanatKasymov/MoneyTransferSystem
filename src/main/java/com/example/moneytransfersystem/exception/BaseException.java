package com.example.moneytransfersystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BaseException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
