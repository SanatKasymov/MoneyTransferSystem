package com.example.moneytransfersystem.repository;

import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, QuerydslPredicateExecutor<Transaction>  {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.from = :cashbox AND t.currency = :currency")
    BigDecimal calculateReceived(Cashbox cashbox, Currency currency);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.to = :cashbox AND t.status = 'ISSUED' AND t.currency = :currency")
    BigDecimal calculateIssued(Cashbox cashbox, Currency currency);

}
