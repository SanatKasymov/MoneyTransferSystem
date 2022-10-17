package com.example.moneytransfersystem.controller.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

    private int status;

    private String error;

    private String message;

    private String path;

    public ErrorDto(HttpStatus httpStatus, String message, String path) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

}
