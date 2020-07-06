package com.journi.challenge.controllers;

import com.journi.challenge.CurrencyConverter;
import com.journi.challenge.models.Product;
import com.journi.challenge.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {

    @Inject
    private ProductsRepository productsRepository;
    CurrencyConverter currencyConverter = new CurrencyConverter();

    /**
     * This method will get all the product details with converted product price as per the country code
     * @param countryCode
     * @return List of products
     */
    @GetMapping("/products")
    public List<Product> list(@RequestParam(name = "countryCode", defaultValue = "AT") String countryCode) {
        return productsRepository.list().stream()
        	.map(product -> new Product(product.getId(), product.getDescription(), currencyConverter.getCurrencyForCountryCode(countryCode),
        		currencyConverter.convertEurToCurrency(currencyConverter.getCurrencyForCountryCode(countryCode), product.getPrice())))
        	.collect(Collectors.toList());
    }
}
