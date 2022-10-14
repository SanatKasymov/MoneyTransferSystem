package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.enums.TransactionStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchTransactionRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime from;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime to;

    BigDecimal amount;

    Currency currency;

    String senderFirstname;

    String senderSurname;

    String senderPatronymic;

    String senderPhone;

    String recipientFirstname;

    String recipientSurname;

    String recipientPatronymic;

    String recipientPhone;

    String description;

    TransactionStatus status;

    Long toCashboxId;

}
