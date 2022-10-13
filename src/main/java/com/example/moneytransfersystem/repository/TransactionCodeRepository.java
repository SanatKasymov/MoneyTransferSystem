package com.example.moneytransfersystem.repository;

import com.example.moneytransfersystem.domain.TransactionCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCodeRepository extends JpaRepository<TransactionCode, Long> {
}
