package com.example.moneytransfersystem.controller.dto;

import com.example.moneytransfersystem.domain.Cashbox;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashboxDto {

    Long id;
    String name;

    public CashboxDto(Cashbox cashbox) {
        this.id = cashbox.getId();
        this.name = cashbox.getName();
    }

}
