package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void getProductBySku() throws Exception {
        Product product = getProductObject();
        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(product));
        Product product1 = productService.getProductBySkuId("FAL-1000003").get();
        assertNotNull(product1);
        assertEquals(product1.getSku(), "FAL-1000003");
        assertEquals(product1.getName(), "500 Zapatilla");
        assertEquals(product1.getPrice(), new BigDecimal(100));
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getSize(), "M");
    }

    @Test
    public void saveProduct() throws Exception {
        Product product = getProductObject();
        productService.saveProduct(product);
        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(product));
        Product product1 = productService.getProductBySkuId("FAL-1000003").get();
        assertNotNull(product1);
        assertEquals(product1.getSku(), "FAL-1000003");
        assertEquals(product1.getName(), "500 Zapatilla");
        assertEquals(product1.getPrice(), new BigDecimal(100));
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getSize(), "M");
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = getProductObject();
        productService.updateProduct(product);
        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(product));
        Product product1 = productService.getProductBySkuId("FAL-1000003").get();
        assertNotNull(product1);
        assertEquals(product1.getSku(), "FAL-1000003");
        assertEquals(product1.getName(), "500 Zapatilla");
        assertEquals(product1.getPrice(), new BigDecimal(100));
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getBrand(), "JEEP");
        assertEquals(product1.getSize(), "M");
    }

    @Test
    public void deleteProduct() throws Exception {
        Product product = getProductObject();
        productService.deleteProduct("FAL-1000003");
        when(productRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        Optional<Product> product1 = productService.getProductBySkuId("FAL-1000003");
        assertEquals(product1.isPresent(), false);
    }

    private Product getProductObject() {
        Product product = new Product();
        product.setSku("FAL-1000003");
        product.setName("500 Zapatilla");
        product.setPrice(new BigDecimal(100));
        product.setSize("M");
        product.setBrand("JEEP");
        product.setPrincipalImageUrl("https://falabella.scene7.com/is/image/Falabella/8406270_1");
        Set<String> set = new HashSet<>();
        set.add("https://falabella.scene7.com/is/image/Falabella/8406270_2");
        set.add("https://falabella.scene7.com/is/image/Falabella/8406270_3");
        product.setImageUrls(set);
        return product;
    }
}
