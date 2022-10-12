package com.example.moneytransfersystem.domain;

import com.example.moneytransfersystem.enums.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private Long id;

    @Column
    private BigDecimal amount;

    @Column
    private Currency currency;

    @Column
    private String senderFullName;

    @Column
    private String senderPhoneNumber;

    @Column
    private String recipientFullName;

    @Column
    private String recipientPhoneNumber;

    @Column
    private String description;

}
