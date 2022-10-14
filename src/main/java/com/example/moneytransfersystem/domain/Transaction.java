package com.example.moneytransfersystem.domain;

import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.enums.TransactionStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "transactions")
public class Transaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTIONS_SEQ")
    @SequenceGenerator(name = "TRANSACTIONS_SEQ", sequenceName = "TRANSACTIONS_SEQ", allocationSize = 1)
    Long id;

    @Column
    BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    Currency currency;

    @Column
    String senderFirstname;

    @Column
    String senderSurname;

    @Column
    String senderPatronymic;

    @Column
    String senderPhoneNumber;

    @Column
    String recipientFirstname;

    @Column
    String recipientSurname;

    @Column
    String recipientPatronymic;

    @Column
    String recipientPhoneNumber;

    @Column
    String description;

    @ManyToOne
    @JoinColumn(name="cashbox_from_id", nullable=false)
    Cashbox from;

    @ManyToOne
    @JoinColumn(name="cashbox_to_id", nullable=false)
    Cashbox to;

    @Column
    @Enumerated(EnumType.STRING)
    TransactionStatus status;

    @OneToOne(mappedBy = "transaction")
    TransactionCode transactionCode;

    static final String fullNameTemplate = "%s %s %s";

    public Transaction(
            BigDecimal amount,
            Currency currency,
            String senderFirstname,
            String senderSurname,
            String senderPatronymic,
            String senderPhoneNumber,
            String recipientFirstname,
            String recipientSurname,
            String recipientPatronymic,
            String recipientPhoneNumber,
            String description,
            Cashbox from,
            Cashbox to,
            TransactionStatus status
    ) {
        this.amount = amount;
        this.currency = currency;
        this.senderFirstname = senderFirstname;
        this.senderSurname = senderSurname;
        this.senderPatronymic = senderPatronymic;
        this.senderPhoneNumber = senderPhoneNumber;
        this.recipientFirstname = recipientFirstname;
        this.recipientSurname = recipientSurname;
        this.recipientPatronymic = recipientPatronymic;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.description = description;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public String getSenderFullname() {
        return String.format(
                fullNameTemplate,
                this.senderFirstname,
                this.senderSurname,
                Objects.toString(this.senderPatronymic, "")
        );
    }

    public String getRecipientFullname() {
        return String.format(
                fullNameTemplate,
                this.recipientFirstname,
                this.recipientSurname,
                Objects.toString(this.recipientPatronymic, "")
        );
    }


}
