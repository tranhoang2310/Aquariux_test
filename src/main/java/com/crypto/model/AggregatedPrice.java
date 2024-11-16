package com.crypto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class AggregatedPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tradingPair;
    private double bidPrice;
    private double askPrice;
    private LocalDateTime updatedAt = LocalDateTime.now();
}
