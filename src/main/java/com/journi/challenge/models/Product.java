package com.journi.challenge.models;

/**
 * Represents a Product the company can sell.
 * Id is of course unique.
 * price is always in Euros.
 */
public class Product {

    private final String id;
    private final String description;
    private final double price;
    private final String currencyCode;

    public Product(String id, String description, String currencyCode, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
