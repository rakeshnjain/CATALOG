package com.example.demo.validation;

import com.example.demo.entity.Product;
import com.example.demo.exception.ExceptionCodes;
import com.example.demo.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Set;

public class ProductValidation {

    public static void validateSku(String sku) {
        if (null == sku || sku.isEmpty()) {
            throw new ProductException(ExceptionCodes.PRODUCT_SKU_MANDATORY,
                    ExceptionCodes.PRODUCT_SKU_MANDATORY.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        String[] skus = sku.split("-");
        if (!(skus.length > 1)) {
            throw new ProductException(ExceptionCodes.PRODUCT_SKU_PATTERN_MISMATCH,
                    ExceptionCodes.PRODUCT_SKU_PATTERN_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        try {
            if (Integer.valueOf(skus[1]) < 1000000 || Integer.valueOf(skus[1]) > 99999999) {
                throw new ProductException(ExceptionCodes.PRODUCT_SKU_RANGE_MISMATCH,
                        ExceptionCodes.PRODUCT_SKU_RANGE_MISMATCH.description(),
                        HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception ex) {
            throw new ProductException(ExceptionCodes.PRODUCT_SKU_PATTERN_MISMATCH,
                    ExceptionCodes.PRODUCT_SKU_PATTERN_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    public static void validateProduct(Product product) {
        ProductValidation.validateSku(product.getSku());
        ProductValidation.validateName(product.getName());
        ProductValidation.validateBrand(product.getBrand());
        ProductValidation.validatePrice(product.getPrice());
        ProductValidation.validatePricipleURL(product.getPrincipalImageUrl());
        ProductValidation.validateImagesURL(product.getImageUrls());
    }

    private static void validateImagesURL(Set<String> imageUrls) {
        if (!CollectionUtils.isEmpty(imageUrls)) {
            for (String imageUrl : imageUrls) {
                try {
                    new URL(imageUrl).toURI();
                }
                catch (Exception e) {
                    throw new ProductException(ExceptionCodes.PRODUCT_IMAGE_URL_PATTERN_MISMATCH,
                            ExceptionCodes.PRODUCT_IMAGE_URL_PATTERN_MISMATCH.description(),
                            HttpStatus.BAD_REQUEST.value());
                }
            }
        }
    }

    private static void validatePricipleURL(String principalImageUrl) {
        if (null == principalImageUrl || principalImageUrl.isEmpty()) {
            throw new ProductException(ExceptionCodes.PRODUCT_PRINCIPLE_IMAGE_URL_MANDATORY,
                    ExceptionCodes.PRODUCT_PRINCIPLE_IMAGE_URL_MANDATORY.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        try {
            new URL(principalImageUrl).toURI();
        }
        catch (Exception e) {
            throw new ProductException(ExceptionCodes.PRODUCT_PRINCIPLE_IMAGE_URL_PATTERN_MISMATCH,
                    ExceptionCodes.PRODUCT_PRINCIPLE_IMAGE_URL_PATTERN_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    private static void validatePrice(BigDecimal price) {
        if (null == price) {
            throw new ProductException(ExceptionCodes.PRODUCT_PRICE_MANDATORY,
                    ExceptionCodes.PRODUCT_PRICE_MANDATORY.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        if (price.compareTo(BigDecimal.ONE) < 0 || price.compareTo(BigDecimal.valueOf(99999999.00)) > 0) {
            throw new ProductException(ExceptionCodes.PRODUCT_PRICE_RANGE_MISMATCH,
                    ExceptionCodes.PRODUCT_PRICE_RANGE_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    private static void validateBrand(String brand) {
        if (null == brand || brand.isEmpty()) {
            throw new ProductException(ExceptionCodes.PRODUCT_BRAND_MANDATORY,
                    ExceptionCodes.PRODUCT_BRAND_MANDATORY.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        if (brand.length() < 3 || brand.length() > 50) {
            throw new ProductException(ExceptionCodes.PRODUCT_BRAND_RANGE_MISMATCH,
                    ExceptionCodes.PRODUCT_BRAND_RANGE_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    private static void validateName(String name) {
        if (null == name || name.isEmpty()) {
            throw new ProductException(ExceptionCodes.PRODUCT_NAME_MANDATORY,
                    ExceptionCodes.PRODUCT_NAME_MANDATORY.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
        if (name.length() < 3 || name.length() > 50) {
            throw new ProductException(ExceptionCodes.PRODUCT_NAME_RANGE_MISMATCH,
                    ExceptionCodes.PRODUCT_NAME_RANGE_MISMATCH.description(),
                    HttpStatus.BAD_REQUEST.value());
        }
    }

}
