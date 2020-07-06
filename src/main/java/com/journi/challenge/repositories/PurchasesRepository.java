package com.journi.challenge.repositories;

import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseStats;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE.withZone(ZoneId.of("UTC"));

        LocalDateTime start = LocalDate.now().atStartOfDay().minusDays(30);

        List<Purchase> recentPurchases = allPurchases
                .stream()
                .filter(p -> p.getTimestamp().isAfter(start))
                .collect(Collectors.toList());

        long countPurchases = recentPurchases.size();
        double totalAmountPurchases = recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(0.0, (a, b) -> a + b);
        return new PurchaseStats(
                formatter.format(recentPurchases.get(0).getTimestamp()),
                formatter.format(recentPurchases.get(recentPurchases.size() - 1).getTimestamp()),
                countPurchases,
                totalAmountPurchases,
                totalAmountPurchases / countPurchases,
                recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(Math::min).orElse(0.0),
                recentPurchases.stream().map(purchase -> purchase.getTotalValue()).reduce(Math::max).orElse(0.0)
        );
    }
}
