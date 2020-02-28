package com.example.market.service;

import com.example.market.document.Trade;
import com.example.market.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepo;

    @EventListener
    public void listen(Trade trade) {
        tradeRepo.save(trade);
    }
}
