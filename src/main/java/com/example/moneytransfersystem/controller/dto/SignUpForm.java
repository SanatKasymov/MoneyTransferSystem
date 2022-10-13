package com.example.moneytransfersystem.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpForm {

    @NotEmpty
    String name;

    @Size(min = 8, max = 24)
    String password;

}
