package com.example.moneytransfersystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cashboxes")
public class Cashbox {

    @Id
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "cashbox")
    private Set<Balance> balances;

}
