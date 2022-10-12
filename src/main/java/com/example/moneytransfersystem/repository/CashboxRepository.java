package com.example.moneytransfersystem.repository;

import com.example.moneytransfersystem.domain.Cashbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashboxRepository extends JpaRepository<Cashbox, Long> {
}
