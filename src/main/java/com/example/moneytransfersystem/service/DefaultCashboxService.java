package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.repository.CashboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultCashboxService implements CashboxService {

    private final CashboxRepository repository;

    @Autowired
    public DefaultCashboxService(CashboxRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cashbox create(String name) {
        // TODO
        return null;
    }
}
