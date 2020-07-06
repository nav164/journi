package com.journi.challenge.models;

public class PurchaseStats {

    private final String from;
    private final String to;
    private final long countPurchases;
    private final double totalAmount;
    private final double avgAmount;
    private final double minAmount;
    private final double maxAmount;

    public PurchaseStats(String from, String to, long countPurchases, double totalAmount, double avgAmount, double minAmount, double maxAmount) {
        this.from = from;
        this.to = to;
        this.countPurchases = countPurchases;
        this.totalAmount = totalAmount;
        this.avgAmount = avgAmount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public long getCountPurchases() {
        return countPurchases;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getAvgAmount() {
        return avgAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

}
