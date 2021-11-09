package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${product.default.page: 1}")
    Integer defaultPage;

    @Value("${product.default.size: 10}")
    Integer defaultSize;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Optional<Product> getProductBySkuId(String skuId) {
        return productRepository.findById(skuId);
    }

    @Override
    public List<Product> getProducts(Integer page, Integer size) {
        Integer currpage = 0;
        if (page == null)
            page = defaultPage;
        if (size == null)
            size = defaultSize;
        Slice<Product> slice = productRepository.findAll(CassandraPageRequest.first(size));
        while(slice.hasNext() && currpage < page) {
            slice = productRepository.findAll(slice.nextPageable());
            currpage++;
        }
        return slice.getContent();
    }

    @Override
    public void saveProduct(Product product) {
       productRepository.save(product);
    }

    @Override
    public void updateProduct(Product newProduct) {
        Optional<Product> oldProduct = productRepository.findById(newProduct.getSku());
        if (oldProduct.isPresent()) {
            Product updatedProduct = oldProduct.get();
            updatedProduct.setSku(newProduct.getSku());
            updatedProduct.setBrand(newProduct.getBrand());
            updatedProduct.setSize(newProduct.getSize());
            updatedProduct.setPrice(newProduct.getPrice());
            updatedProduct.setName(newProduct.getName());
            updatedProduct.setPrincipalImageUrl(newProduct.getPrincipalImageUrl());
            updatedProduct.setImageUrls(newProduct.getImageUrls());
            productRepository.save(updatedProduct);
        }
    }

    @Override
    public void deleteProduct(String skuId) {
        productRepository.deleteById(skuId);
    }
}
