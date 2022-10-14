package com.example.moneytransfersystem.service;

import com.example.moneytransfersystem.controller.dto.CreateTransactionRequest;
import com.example.moneytransfersystem.controller.dto.SearchTransactionRequest;
import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.domain.TransactionCode;
import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.enums.TransactionStatus;
import com.example.moneytransfersystem.exception.NotFoundException;
import com.example.moneytransfersystem.repository.TransactionRepository;
import com.example.moneytransfersystem.repository.filter.TransactionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionCodeService transactionCodeService;

    @Autowired
    public DefaultTransactionService(TransactionRepository repository, TransactionCodeService transactionCodeService) {
        this.repository = repository;
        this.transactionCodeService = transactionCodeService;
    }

    @Override
    @Transactional
    public TransactionCode create(Cashbox from, Cashbox to, CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
                request.getAmount(),
                request.getCurrency(),
                request.getSenderFirstname(),
                request.getSenderSurname(),
                request.getSenderPatronymic(),
                request.getSenderPhone(),
                request.getRecipientFirstname(),
                request.getRecipientSurname(),
                request.getRecipientPatronymic(),
                request.getRecipientPhone(),
                request.getDescription(),
                from,
                to,
                TransactionStatus.CREATED
        );
        repository.save(transaction);

        return transactionCodeService.create(transaction);
    }

    @Override
    @Transactional
    public void complete(Cashbox cashbox, Long id) {
        Transaction transaction = repository.findById(id).orElseThrow(NotFoundException::new);

        if (!Objects.equals(transaction.getTo(), cashbox)) {
            throw new AccessDeniedException("Access to transaction denied");
        }

        transaction.setStatus(TransactionStatus.ISSUED);
        repository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAll(SearchTransactionRequest request) {
        TransactionFilter filter = new TransactionFilter(request);
        return (List<Transaction>) repository.findAll(filter.toPredicate());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(Cashbox cashbox, Currency currency) {
        BigDecimal received = repository.calculateReceived(cashbox, currency);
        BigDecimal issued = repository.calculateIssued(cashbox, currency);

        return received.subtract(issued).setScale(3, RoundingMode.DOWN);
    }

}
