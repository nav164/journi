package com.journi.challenge.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListProductsWithCurrencyCodeAndConvertedPriceDefault() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", IsEqual.equalTo(4)))
                .andExpect(jsonPath("$[*].currencyCode", IsNot.not(IsEmptyCollection.empty())))
                .andExpect(jsonPath("$[0].currencyCode", IsEqual.equalTo("EUR")))
                .andExpect(jsonPath("$[0].price", IsEqual.equalTo(25.0)));
    }

    @Test
    public void shouldListProductsWithCurrencyCodeAndConvertedPriceBR() throws Exception {
        mockMvc.perform(get("/products?countryCode=BR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", IsEqual.equalTo(4)))
                .andExpect(jsonPath("$[*].currencyCode", IsNot.not(IsEmptyCollection.empty())))
                .andExpect(jsonPath("$[0].currencyCode", IsEqual.equalTo("BRL")))
                .andExpect(jsonPath("$[0].price", IsEqual.equalTo(128.7)));
    }

    @Test
    public void shouldListProductsWithCurrencyCodeEURWhenCountryCodeNonSupported() throws Exception {
        mockMvc.perform(get("/products?countryCode=JP"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", IsEqual.equalTo(4)))
                .andExpect(jsonPath("$[*].currencyCode", IsNot.not(IsEmptyCollection.empty())))
                .andExpect(jsonPath("$[0].currencyCode", IsEqual.equalTo("EUR")))
                .andExpect(jsonPath("$[0].price", IsEqual.equalTo(25.0)));
    }
    
    @Test
    public void shouldListProductsWithCurrencyCodeAndConvertedPriceHU() throws Exception {
        mockMvc.perform(get("/products?countryCode=HU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].currencyCode", IsNot.not(IsEmptyCollection.empty())))
                .andExpect(jsonPath("$[0].currencyCode", IsEqual.equalTo("HUF")))
                .andExpect(jsonPath("$[0].price", IsEqual.equalTo(8392.5)));
    }
}
