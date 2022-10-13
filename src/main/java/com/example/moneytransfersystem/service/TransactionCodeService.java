package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.domain.TransactionCode;

public interface TransactionCodeService {

    TransactionCode create(Transaction transaction);

}
