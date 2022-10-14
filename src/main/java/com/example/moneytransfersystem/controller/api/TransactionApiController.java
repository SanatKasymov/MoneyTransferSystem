package com.example.moneytransfersystem.controller.api;

import com.example.moneytransfersystem.annotation.CurrentCashbox;
import com.example.moneytransfersystem.controller.dto.CreateTransactionRequest;
import com.example.moneytransfersystem.controller.dto.SearchTransactionRequest;
import com.example.moneytransfersystem.controller.dto.TransactionCodeDto;
import com.example.moneytransfersystem.controller.dto.TransactionDto;
import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.domain.TransactionCode;
import com.example.moneytransfersystem.service.CashboxService;
import com.example.moneytransfersystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionApiController {

    private final TransactionService transactionService;
    private final CashboxService cashboxService;

    @Autowired
    public TransactionApiController(TransactionService transactionService, CashboxService cashboxService) {
        this.transactionService = transactionService;
        this.cashboxService = cashboxService;
    }

    @GetMapping
    public List<TransactionDto> get(@CurrentCashbox Cashbox cashbox, SearchTransactionRequest request) {
        List<Transaction> transactions = transactionService.getAll(request);
        return transactions
                .stream()
                .map(t -> new TransactionDto(t, t.getTo().equals(cashbox) ? t.getTransactionCode().getCode() : null))
                .collect(Collectors.toList());
    }

    @PostMapping
    public TransactionCodeDto transfer(
            @CurrentCashbox Cashbox cashbox,
            @Valid @RequestBody CreateTransactionRequest request
    ) {
        Cashbox to = cashboxService.get(request.getCashboxId());

        TransactionCode transactionCode = transactionService.create(cashbox, to, request);
        return new TransactionCodeDto(transactionCode);
    }

    @PutMapping("/complete/{id:\\d+}")
    public void complete(@CurrentCashbox Cashbox cashbox, @PathVariable Long id) {
        transactionService.complete(cashbox, id);
    }

}
