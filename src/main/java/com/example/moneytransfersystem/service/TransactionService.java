package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.controller.dto.CreateTransactionRequest;
import com.example.moneytransfersystem.domain.Transaction;

public interface TransactionService {

    Transaction create(CreateTransactionRequest request);

}
