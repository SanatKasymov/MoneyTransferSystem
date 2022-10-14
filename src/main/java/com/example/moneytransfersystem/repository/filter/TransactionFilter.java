package com.example.moneytransfersystem.repository.filter;

import com.example.moneytransfersystem.controller.dto.SearchTransactionRequest;
import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.enums.TransactionStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.moneytransfersystem.domain.QTransaction.transaction;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionFilter {

    LocalDateTime from;

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

    public TransactionFilter(SearchTransactionRequest request) {
        this.from = request.getFrom();
        this.to = request.getTo();
        this.amount = request.getAmount();
        this.currency = request.getCurrency();
        this.senderFirstname = getValue(request.getSenderFirstname());
        this.senderSurname = getValue(request.getSenderSurname());
        this.senderPatronymic = getValue(request.getSenderPatronymic());
        this.senderPhone = getValue(request.getSenderPhone());
        this.recipientFirstname = getValue(request.getRecipientFirstname());
        this.recipientSurname = getValue(request.getRecipientSurname());
        this.recipientPatronymic = getValue(request.getRecipientPatronymic());
        this.recipientPhone = getValue(request.getRecipientPatronymic());
        this.description = getValue(request.getDescription());
        this.status = request.getStatus();
        this.toCashboxId = request.getToCashboxId();
    }

    private String getValue(String val) {
        if (val == null || val.isEmpty()) {
            return null;
        }

        return val;
    }

    public Predicate toPredicate() {
        BooleanBuilder builder = new BooleanBuilder();

        Optional.ofNullable(this.from).ifPresent(it -> builder.and(transaction.createdAt.after(it)));
        Optional.ofNullable(this.to).ifPresent(it -> builder.and(transaction.createdAt.before(it)));
        Optional.ofNullable(this.amount).ifPresent(it -> builder.and(transaction.amount.eq(it)));
        Optional.ofNullable(this.currency).ifPresent(it -> builder.and(transaction.currency.eq(it)));
        Optional.ofNullable(this.senderFirstname).ifPresent(it -> builder.and(transaction.senderFirstname.eq(it)));
        Optional.ofNullable(this.senderSurname).ifPresent(it -> builder.and(transaction.senderSurname.eq(it)));
        Optional.ofNullable(this.senderPatronymic).ifPresent(it -> builder.and(transaction.senderPatronymic.eq(it)));
        Optional.ofNullable(this.senderPhone).ifPresent(it -> builder.and(transaction.senderPhoneNumber.eq(it)));
        Optional.ofNullable(this.recipientFirstname).ifPresent(it -> builder.and(transaction.recipientFirstname.eq(it)));
        Optional.ofNullable(this.recipientSurname).ifPresent(it -> builder.and(transaction.recipientSurname.eq(it)));
        Optional.ofNullable(this.recipientPatronymic).ifPresent(it -> builder.and(transaction.recipientPatronymic.eq(it)));
        Optional.ofNullable(this.recipientPhone).ifPresent(it -> builder.and(transaction.recipientPhoneNumber.eq(it)));
        Optional.ofNullable(this.description).ifPresent(it -> builder.and(transaction.description.eq(it)));
        Optional.ofNullable(this.status).ifPresent(it -> builder.and(transaction.status.eq(it)));
        Optional.ofNullable(this.toCashboxId).ifPresent(it -> builder.and(transaction.to.id.eq(it)));

        return builder;
    }

}
