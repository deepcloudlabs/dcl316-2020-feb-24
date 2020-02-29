package com.example.market.service;

import com.example.market.document.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MarketWebSocketService {
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void listen(Trade trade){
        messagingTemplate.convertAndSend("/topic/changes", trade);
    }
}
