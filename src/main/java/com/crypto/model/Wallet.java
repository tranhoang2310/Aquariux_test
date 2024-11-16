package com.crypto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String currency;
    private double balance;
}