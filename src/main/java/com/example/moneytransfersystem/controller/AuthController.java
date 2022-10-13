package com.example.moneytransfersystem.controller;

import com.example.moneytransfersystem.annotation.CurrentCashbox;
import com.example.moneytransfersystem.controller.dto.SignUpForm;
import com.example.moneytransfersystem.domain.Cashbox;
import com.example.moneytransfersystem.exception.CashboxAlreadyExistsException;
import com.example.moneytransfersystem.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final CashboxService cashboxService;

    @Autowired
    public AuthController(CashboxService cashboxService) {
        this.cashboxService = cashboxService;
    }


    @GetMapping(value = { "/", "login" })
    public String login(@CurrentCashbox Cashbox cashbox) {
        if (cashbox == null) {
            return "login.html";
        } else {
            return "redirect:/cabinet";
        }
    }

    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute("form") SignUpForm from) {
        return "sign-up";
    }

    @PostMapping(value = "/sign-up")
    public String signUp(@Valid @ModelAttribute("form") SignUpForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "sign-up";
        }

        try {
            cashboxService.create(form.getName(), form.getPassword());
        } catch (CashboxAlreadyExistsException ex) {
            binding.rejectValue("name", "error.exists");
            return "sign-up";
        }

        return "sign-up-successful";
    }

}
