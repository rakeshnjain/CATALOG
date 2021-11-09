package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${microservice.contextPath}")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/sku/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable(value = "sku") String sku) {
        ProductValidation.validateSku(sku);
        Optional<Product> product = productService.getProductBySkuId(sku);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(product.get(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            value = "/skus")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        List<Product> products = productService.getProducts(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "/sku")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        ProductValidation.validateProduct(product);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT,
            value = "/sku")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        ProductValidation.validateProduct(product);
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE,
            value = "/sku/{sku}")
    public ResponseEntity deleteProduct(@PathVariable(value = "sku") String sku) {
        ProductValidation.validateSku(sku);
        productService.deleteProduct(sku);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
