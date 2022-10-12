package com.example.moneytransfersystem.repository;

import com.example.moneytransfersystem.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
