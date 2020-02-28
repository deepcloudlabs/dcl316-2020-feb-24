package com.example.market.repository;

import com.example.market.document.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade,String> {
}
