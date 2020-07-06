package com.journi.challenge.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.journi.challenge.CurrencyConverter;
import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseRequest;
import com.journi.challenge.models.PurchaseStats;
import com.journi.challenge.repositories.PurchasesRepository;

@RestController
public class PurchasesController {

    @Inject
    private PurchasesRepository purchasesRepository;
    CurrencyConverter currencyConverter = new CurrencyConverter();
    
    /**
     * This method will fetch all the statistic for purchase of last 30 days
     * @return Purchase statistic
     */
    @GetMapping("/purchases/statistics")
    public PurchaseStats getStats() {
        return purchasesRepository.getLast30DaysStats();
    }

    /**
     * This method will save the purchase details
     * @param purchaseRequest
     * @return Details of purchase
     */
    @PostMapping("/purchases")
    public Purchase save(@RequestBody PurchaseRequest purchaseRequest) {
        Purchase newPurchase = new Purchase(
                purchaseRequest.getInvoiceNumber(),
                LocalDateTime.parse(purchaseRequest.getDateTime(), DateTimeFormatter.ISO_DATE_TIME),
                purchaseRequest.getProductIds(),
                purchaseRequest.getCustomerName(),
                currencyConverter.convertCurrencyToEur(purchaseRequest.getCurrencyCode(), purchaseRequest.getAmount()),
                "EUR"
        );
        purchasesRepository.save(newPurchase);
        return newPurchase;
    }
}
