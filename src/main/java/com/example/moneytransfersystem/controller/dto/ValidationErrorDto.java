package com.example.moneytransfersystem.controller.dto;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValidationErrorDto {
    private String path;
    private String message;
    private List<FieldErrorDto> errors;

    public ValidationErrorDto(String path, BindingResult bindingResult) {
        this.path = path;
        this.message = String.format("Validation failed for object='%s'", bindingResult.getObjectName());
        this.errors = bindingResult.getFieldErrors().stream().map(FieldErrorDto::new).collect(Collectors.toList());
    }

    @Data
    static class FieldErrorDto {
        private String objectName;
        private String field;
        private String message;
        private String code;

        public FieldErrorDto(FieldError fieldError) {
            this.objectName = fieldError.getObjectName();
            this.field = fieldError.getField();
            this.message = fieldError.getDefaultMessage();
            this.code = fieldError.getCode();
        }

    }

}
