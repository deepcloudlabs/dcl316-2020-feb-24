package com.example.market.repository;

import com.example.market.document.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock ,String> {
}
