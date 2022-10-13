package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.controller.dto.CreateTransactionRequest;
import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.domain.TransactionCode;

import java.util.List;

public interface TransactionService {

    TransactionCode create(Cashbox from, Cashbox to, CreateTransactionRequest request);

    void complete(Cashbox cashbox, Long id);

    List<Transaction> getAll();

}
