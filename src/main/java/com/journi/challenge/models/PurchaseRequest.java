package com.journi.challenge.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Request for Purchase
 * amount is the value of the total purchase, in given currencyCode
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseRequest {

    private final String invoiceNumber;
    private final String customerName;
    private final String dateTime;
    private final List<String> productIds;
    private final Double amount;
    private final String currencyCode;

    public PurchaseRequest() {
	this.invoiceNumber = null;
        this.customerName = null;
        this.dateTime = "01-Jan-2017";
        this.productIds = null;
        this.amount = null;
        this.currencyCode = null;
    }
    
    public PurchaseRequest(String invoiceNumber, String customerName, String dateTime, List<String> productIds, Double amount, String currencyCode) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.dateTime = dateTime;
        this.productIds = productIds;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
