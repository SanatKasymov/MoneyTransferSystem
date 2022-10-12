package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.controller.dto.CreateTransactionRequest;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository repository;

    public DefaultTransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Transaction create(CreateTransactionRequest request) {
        // TODO
        return null;
    }
}
