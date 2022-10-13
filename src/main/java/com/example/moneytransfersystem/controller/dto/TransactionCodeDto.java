package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.domain.TransactionCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCodeDto {

    String code;

    public TransactionCodeDto(TransactionCode transactionCode) {
        this.code = transactionCode.getCode();
    }

}
