package com.journi.challenge.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.inject.Singleton;

import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseStats;

@Named
@Singleton
public class PurchasesRepository {

    private final List<Purchase> allPurchases = new ArrayList<>();

    public List<Purchase> list() {
        return allPurchases;
    }

    /**
     * This method will add the purchase to the list
     * @param purchase purchase object
     */
    public void save(Purchase purchase) {
        allPurchases.add(purchase);
        allPurchases.sort(Comparator.comparing(Purchase::getTimestamp));
    }

    /**
     * This method will get the purchase stats of last 30 days
     * @return Purchase stats
     */
    public PurchaseStats getLast30DaysStats() {
	PurchaseStats purchaseStats = null;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE.withZone(ZoneId.of("UTC"));

        LocalDateTime start = LocalDate.now().atStartOfDay().minusDays(30);

        List<Purchase> recentPurchases = allPurchases
                .stream()
                .filter(p -> p.getTimestamp().isAfter(start))
                .collect(Collectors.toList());
        long countPurchases = recentPurchases.size();
        double totalAmountPurchases = recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(0.0, (a, b) -> a + b);
        if(recentPurchases.size() > 0) {
            purchaseStats = new PurchaseStats(
                    formatter.format(recentPurchases.get(0).getTimestamp()),
                    formatter.format(recentPurchases.get(recentPurchases.size() - 1).getTimestamp()),
                    countPurchases,
                    totalAmountPurchases,
                    totalAmountPurchases / countPurchases,
                    recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(Math::min).orElse(0.0),
                    recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(Math::max).orElse(0.0)
            ); 
        } else {
            purchaseStats = new PurchaseStats("dd-MM-yyyy", "dd-MM-yyyy", 0, 0, 0, 0, 0);
        }
        
        return purchaseStats;
    }
}
