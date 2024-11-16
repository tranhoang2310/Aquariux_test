package com.crypto.scheduler;

import com.crypto.service.AggregatedPriceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PriceScheduler {
    private final AggregatedPriceService priceService;

    public PriceScheduler(AggregatedPriceService priceService) {
        this.priceService = priceService;
    }

    @Scheduled(fixedRate = 10000)
    public void updatePrices() {
        priceService.fetchAndSaveBestPrices();
    }
}
