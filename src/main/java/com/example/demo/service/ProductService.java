package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductBySkuId(String skuId);

    List<Product> getProducts(Integer page, Integer size);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String skuId);
}
