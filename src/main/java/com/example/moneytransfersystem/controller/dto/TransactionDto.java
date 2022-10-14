package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.enums.TransactionStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {

    Long id;

    BigDecimal amount;

    Currency currency;

    String sender;

    String senderPhone;

    String recipient;

    String recipientPhone;

    String description;

    TransactionStatus status;

    LocalDateTime created_at;

    String code;

    public TransactionDto(Transaction transaction, String transactionCode) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.currency = transaction.getCurrency();
        this.sender = transaction.getSenderFullname();
        this.senderPhone = transaction.getSenderPhoneNumber();
        this.recipient = transaction.getRecipientFullname();
        this.recipientPhone = transaction.getRecipientPhoneNumber();
        this.description = transaction.getDescription();
        this.status = transaction.getStatus();
        this.created_at = transaction.getCreatedAt();
        this.code = transactionCode;
    }

}
