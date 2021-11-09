package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductContollerTest {

    @Mock
    ProductService productService;
    @InjectMocks
    ProductController productController;

    @Test
    public void getProductBySku() throws Exception {
        Product product = getProductObject();
        when(productService.getProductBySkuId(anyString()))
                .thenReturn(Optional.of(product));
        ResponseEntity responseEntity = productController.getProductBySku("FAL-1000003");
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void getAllProduct() throws Exception {
        Product product = getProductObject();
        when(productService.getProducts(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(product));
        ResponseEntity responseEntity = productController.getProducts(1,10);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void saveProduct() throws Exception {
        Product product = getProductObject();
        ResponseEntity responseEntity = productController.saveProduct(product);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void updateProduct() throws Exception {
        Product product = getProductObject();
        ResponseEntity responseEntity = productController.updateProduct(product);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void deleteProduct() throws Exception {
        Product product = getProductObject();
        ResponseEntity responseEntity = productController.deleteProduct(anyString());
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);

    }

    private Product getProductObject() {
        Product product = new Product();
        product.setSku("FAL-1000003");
        product.setName("500 Zapatilla");
        product.setPrice(new BigDecimal(100));
        product.setBrand("JEEP");
        product.setSize("M");
        product.setPrincipalImageUrl("https://falabella.scene7.com/is/image/Falabella/8406270_1");
        Set<String> set = new HashSet<>();
        set.add("https://falabella.scene7.com/is/image/Falabella/8406270_2");
        set.add("https://falabella.scene7.com/is/image/Falabella/8406270_3");
        product.setImageUrls(set);
        return product;
    }
}
