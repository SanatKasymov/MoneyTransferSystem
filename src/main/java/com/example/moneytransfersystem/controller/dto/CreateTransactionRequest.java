package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.enums.Currency;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTransactionRequest {

    @NotNull
    BigDecimal amount;

    @NotNull
    Currency currency;

    @NotEmpty
    String senderFirstname;

    @NotEmpty
    String senderSurname;

    String senderPatronymic;

    @NotEmpty
    String senderPhone;

    @NotEmpty
    String recipientFirstname;

    @NotEmpty
    String recipientSurname;

    String recipientPatronymic;

    @NotEmpty
    String recipientPhone;

    @NotEmpty
    String description;

    @NotNull
    Long cashboxId;

}
