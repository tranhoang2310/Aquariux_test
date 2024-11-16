package com.crypto.repository;

import com.crypto.model.AggregatedPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    AggregatedPrice findByTradingPair(String tradingPair);
}
