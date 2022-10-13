package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.domain.Cashbox;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.List;

public interface CashboxService extends UserDetailsService {

    BigDecimal getBalance(Cashbox cashbox);

    Cashbox create(String name, String password);

    Cashbox get(Long id);

    List<Cashbox> getAll();

}
