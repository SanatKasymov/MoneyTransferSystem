package com.example.moneytransfersystem.controller;

import com.example.moneytransfersystem.controller.dto.CreateCashboxRequest;
import com.example.moneytransfersystem.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cashbox")
public class CashboxController {

    private final CashboxService cashboxService;

    @Autowired
    public CashboxController(CashboxService cashboxService) {
        this.cashboxService = cashboxService;
    }

    @GetMapping
    public String get() {
        return "cashbox";
    }

    @PostMapping
    public String create(@RequestBody CreateCashboxRequest request) {
        cashboxService.create(request.name);
        return "cashbox";
    }

}
