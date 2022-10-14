package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.enums.Currency;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceDto {

    Currency currency;

    BigDecimal amount;

}
