package com.example.market.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
public class Stock {
    @Id
    private String symbol;
    private String company;
    private double price;

    public Stock() {
    }

    public Stock(String symbol, String company, double price) {
        this.symbol = symbol;
        this.company = company;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                '}';
    }
}
