package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.enums.Currency;

import java.math.BigDecimal;

public class CreateTransactionRequest {

    BigDecimal amount;

    Currency currency;

    String senderName;

    String senderSurname;

    String senderPatronymic;

    String senderPhoneNumber;

    String recipientName;

    String recipientSurname;

    String recipientPatronymic;

    String recipientPhoneNumber;

    String description;

}
