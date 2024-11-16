package com.crypto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TradingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String tradingPair;
    private String transactionType; // BUY or SELL
    private double quantity;
    private double price;
    private LocalDateTime createdAt = LocalDateTime.now();
}
