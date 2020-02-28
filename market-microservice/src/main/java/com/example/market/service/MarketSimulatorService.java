package com.example.market.service;

import com.example.market.document.Trade;
import com.example.market.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MarketSimulatorService {
    @Autowired private StockRepository stockRepo;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(fixedRate = 1000)
    public void simulateTrading(){
        stockRepo.findAll().forEach( stock -> {
            Trade trade = new Trade(stock.getSymbol());
            double price = stock.getPrice();
            ThreadLocalRandom current = ThreadLocalRandom.current();
            double factor = current.nextDouble(0.95, 1.05);
            trade.setPrice(price * factor);
            trade.setQuantity(current.nextDouble(10,1000));
            publisher.publishEvent(trade);
        });
    }
}
