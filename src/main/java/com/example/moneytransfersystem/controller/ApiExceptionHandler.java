package com.example.moneytransfersystem.controller;

import com.example.moneytransfersystem.controller.dto.ErrorDto;
import com.example.moneytransfersystem.controller.dto.ValidationErrorDto;
import com.example.moneytransfersystem.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    protected ResponseEntity<ErrorDto> handleBaseError(BaseException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        ErrorDto response = new ErrorDto(ex.getStatus(), ex.getMessage(), path);
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    protected ValidationErrorDto handleBindException(BindException ex, HttpServletRequest request) {
        return new ValidationErrorDto(request.getRequestURI(), ex.getBindingResult());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    protected ValidationErrorDto handleMethodArgumentException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        return new ValidationErrorDto(request.getRequestURI(), ex.getBindingResult());
    }

}
