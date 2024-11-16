package com.crypto.repository;

import com.crypto.model.TradingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradingTransactionRepository extends JpaRepository<TradingTransaction, Long> {
    List<TradingTransaction> findByUserId(Long userId);
}
