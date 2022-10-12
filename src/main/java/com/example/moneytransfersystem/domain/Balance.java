package com.example.moneytransfersystem.domain;

import com.example.moneytransfersystem.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "balances")
public class Balance {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="cashbox_id", nullable=false)
    private Cashbox cashbox;

    @Column
    @Enumerated
    private Currency currency;

    @Column
    private BigDecimal amount;

}
