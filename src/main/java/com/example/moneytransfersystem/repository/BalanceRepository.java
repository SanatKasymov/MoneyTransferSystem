package com.example.moneytransfersystem.repository;

import com.example.moneytransfersystem.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
