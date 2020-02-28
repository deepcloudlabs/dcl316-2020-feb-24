package com.example.market;

import com.example.market.document.Stock;
import com.example.market.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketMicroserviceApplication implements ApplicationRunner {
    @Autowired private StockRepository stockRepo;

    public static void main(String[] args) {
        SpringApplication.run(MarketMicroserviceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ( stockRepo.findAll().size() == 0){
            Stock stock =
                    new Stock("GARAN", "Garanti Bank",
                            100);
            stockRepo.save(stock);
        }
    }
}
