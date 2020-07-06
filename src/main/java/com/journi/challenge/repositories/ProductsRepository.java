package com.journi.challenge.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import com.journi.challenge.models.Product;

@Named
@Singleton
public class ProductsRepository {
    private List<Product> allProducts = new ArrayList<>();
    {
        allProducts.add(new Product("photobook-square-soft-cover", "Photobook Square with Soft Cover", "EUR", 25.0));
        allProducts.add(new Product("photobook-square-hard-cover", "Photobook Square with Hard Cover", "EUR", 30.0));
        allProducts.add(new Product("photobook-landscape-soft-cover", "Photobook Landscape with Soft Cover", "EUR", 35.0));
        allProducts.add(new Product("photobook-landscape-hard-cover", "Photobook Landscape with Hard Cover", "EUR", 45.0));
    }

    /**
     * This method will fetch all the product
     * @return List of products
     */
    public List<Product> list() {
        return allProducts;
    }
}
