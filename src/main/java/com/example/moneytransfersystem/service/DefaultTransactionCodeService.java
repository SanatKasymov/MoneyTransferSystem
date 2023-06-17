package com.example.moneytransfersystem.service;


import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.domain.TransactionCode;
import com.example.moneytransfersystem.repository.TransactionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class DefaultTransactionCodeService implements TransactionCodeService {

    private final TransactionCodeRepository repository;

    @Autowired
    public DefaultTransactionCodeService(TransactionCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public TransactionCode create(Transaction transaction) {
        String code = generateCode();
        TransactionCode transactionCode = new TransactionCode(code, transaction);

        return repository.save(transactionCode);
    }

    private String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
