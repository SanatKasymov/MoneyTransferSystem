package com.example.moneytransfersystem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Entity
@Table(name = "transactions_codes")
public class TransactionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTIONS_CODES_SEQ")
    @SequenceGenerator(name = "TRANSACTIONS_CODES_SEQ", sequenceName = "TRANSACTIONS_CODES_SEQ", allocationSize = 1)
    Long id;

    @Column
    String code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    Transaction transaction;

    public TransactionCode(String code, Transaction transaction) {
        this.code = code;
        this.transaction = transaction;
    }

}
