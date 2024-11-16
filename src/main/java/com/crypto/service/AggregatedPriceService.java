package com.crypto.service;

import com.crypto.model.AggregatedPrice;
import com.crypto.repository.AggregatedPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatedPriceService {
    private final AggregatedPriceRepository aggregatedPriceRepository;
    private final RestTemplate restTemplate;

    public AggregatedPriceService(AggregatedPriceRepository repository) {
        this.aggregatedPriceRepository = repository;
        this.restTemplate = new RestTemplate();
    }

    public void fetchAndSaveBestPrices() {
        // Fetch Binance prices
        String binanceUrl = "https://api.binance.com/api/v3/ticker/bookTicker";
        String huobiUrl = "https://api.huobi.pro/market/tickers";

        // Parse API response
        double binanceBid = 20000.0;
        double binanceAsk = 21000.0;
        double huobiBid = 19950.0;
        double huobiAsk = 20500.0;

        double bestBid = Math.max(binanceBid, huobiBid);
        double bestAsk = Math.min(binanceAsk, huobiAsk);

        AggregatedPrice price = new AggregatedPrice();
        price.setTradingPair("BTCUSDT");
        price.setBidPrice(bestBid);
        price.setAskPrice(bestAsk);
        aggregatedPriceRepository.save(price);
    }
}
