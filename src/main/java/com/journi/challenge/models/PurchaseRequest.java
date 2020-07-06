package com.journi.challenge.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Request for Purchase
 * amount is the value of the total purchase, in given currencyCode
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class PurchaseRequest {

    @NotNull(message = "Invoice number can not be empty")
    @NotBlank(message = "Invoice number can not be blank")
    private final String invoiceNumber;
    
    @NotNull(message = "Customer name can not be empty")
    @NotBlank(message = "Customer name can not be blank")
    private final String customerName;
    
    @NotNull(message = "Date time can not be empty")
    @NotBlank(message = "Date time can not be blank")
    private final String dateTime;
    
    @NotNull(message = "Product id list can not be empty")
    private final List<String> productIds;
    
    @NotNull(message = "Amount can not be empty")
    private final double amount;
    
    @NotNull(message = "Currency code can not be empty")
    @NotBlank(message = "Currency code can not be blank")
    private final String currencyCode;

    public PurchaseRequest() {
	this.invoiceNumber = null;
        this.customerName = null;
        this.dateTime = "01-Jan-2017";
        this.productIds = null;
        this.amount = 0.0;
        this.currencyCode = null;
    }
    
    public PurchaseRequest(String invoiceNumber, String customerName, String dateTime, List<String> productIds, double amount, String currencyCode) {
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

    public double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
