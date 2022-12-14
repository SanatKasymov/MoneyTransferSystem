package com.example.moneytransfersystem.controller.dto;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCashboxRequest {

    @NotNull
    @Size(min = 2, max = 24)
    String name;

}
