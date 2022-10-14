package com.example.moneytransfersystem.controller;

import com.example.moneytransfersystem.annotation.CurrentCashbox;
import com.example.moneytransfersystem.controller.dto.BalanceDto;
import com.example.moneytransfersystem.controller.dto.CashboxDto;
import com.example.moneytransfersystem.controller.dto.SearchTransactionRequest;
import com.example.moneytransfersystem.controller.dto.TransactionDto;
import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.domain.Transaction;
import com.example.moneytransfersystem.enums.Currency;
import com.example.moneytransfersystem.service.CashboxService;
import com.example.moneytransfersystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private final TransactionService transactionService;
    private final CashboxService cashboxService;

    @Autowired
    public CabinetController(TransactionService transactionService, CashboxService cashboxService) {
        this.transactionService = transactionService;
        this.cashboxService = cashboxService;
    }

    @GetMapping
    public String cabinet(@CurrentCashbox Cashbox cashbox, SearchTransactionRequest request, Model model) {
        List<Transaction> transactions = transactionService.getAll(request);
        List<TransactionDto> transactionDtos = transactions
                .stream()
                .map(t -> new TransactionDto(t, Objects.equals(t.getTo(), cashbox) ? t.getTransactionCode().getCode() : null))
                .collect(toList());

        List<Cashbox> cashboxes = cashboxService.getAll()
                .stream()
                .filter(c -> !Objects.equals(c.getId(), cashbox.getId()))
                .collect(toList());
        List<CashboxDto> cashboxDtos = cashboxes.stream().map(CashboxDto::new).collect(toList());

        List<BalanceDto> balances = Arrays
                .stream(Currency.values())
                .map(c -> new BalanceDto(c, transactionService.getBalance(cashbox, c)))
                .collect(toList());

        model.addAttribute("transactions", transactionDtos);
        model.addAttribute("cashboxes", cashboxDtos);
        model.addAttribute("balances", balances);

        return "cabinet";
    }


}
