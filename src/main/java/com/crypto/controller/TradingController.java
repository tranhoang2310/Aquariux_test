package com.crypto.controller;

import com.crypto.model.AggregatedPrice;
import com.crypto.model.TradingTransaction;
import com.crypto.model.Wallet;
import com.crypto.repository.AggregatedPriceRepository;
import com.crypto.repository.TradingTransactionRepository;
import com.crypto.repository.WalletRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TradingController {
    private final AggregatedPriceRepository priceRepository;
    private final WalletRepository walletRepository;
    private final TradingTransactionRepository transactionRepository;

    public TradingController(AggregatedPriceRepository priceRepo,
                             WalletRepository walletRepo,
                             TradingTransactionRepository transactionRepo) {
        this.priceRepository = priceRepo;
        this.walletRepository = walletRepo;
        this.transactionRepository = transactionRepo;
    }

    @GetMapping("/prices/{tradingPair}")
    public ResponseEntity<AggregatedPrice> getBestPrice(@PathVariable String tradingPair) {
        return ResponseEntity.ok(priceRepository.findByTradingPair(tradingPair));
    }

    @PostMapping("/trade")
    public ResponseEntity<String> trade(@RequestBody TradingTransaction tradeRequest) {
        Wallet wallet = walletRepository.findByUserId(tradeRequest.getUserId()).get(0);
        double amount = tradeRequest.getQuantity() * tradeRequest.getPrice();

        if ("BUY".equalsIgnoreCase(tradeRequest.getTransactionType()) && wallet.getBalance() >= amount) {
            wallet.setBalance(wallet.getBalance() - amount);
        } else if ("SELL".equalsIgnoreCase(tradeRequest.getTransactionType())) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else {
            return ResponseEntity.badRequest().body("Insufficient balance!");
        }

        walletRepository.save(wallet);
        transactionRepository.save(tradeRequest);
        return ResponseEntity.ok("Trade successful");
    }

    @GetMapping("/wallet/{userId}")
    public ResponseEntity<List<Wallet>> getWallet(@PathVariable Long userId) {
        return ResponseEntity.ok(walletRepository.findByUserId(userId));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<TradingTransaction>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionRepository.findByUserId(userId));
    }
}
