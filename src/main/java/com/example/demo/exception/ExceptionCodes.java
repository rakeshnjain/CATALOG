package com.example.demo.exception;

public enum ExceptionCodes {

    PRODUCT_SKU_MANDATORY("PRODUCT_001", "Sku is mandatory"),
    PRODUCT_SKU_PATTERN_MISMATCH("PRODUCT_002", "Sku pattern is wrong"),
    PRODUCT_SKU_RANGE_MISMATCH("PRODUCT_003", "Sku range should be between 1000000 and 99999999"),
    PRODUCT_NAME_MANDATORY("PRODUCT_004", "Name is mandatory"),
    PRODUCT_NAME_RANGE_MISMATCH("PRODUCT_005", "Name should have 3 and 50 charactors"),
    PRODUCT_BRAND_MANDATORY("PRODUCT_006", "Brand is mandatory"),
    PRODUCT_BRAND_RANGE_MISMATCH("PRODUCT_007", "Brand should have 3 and 50 charactors"),
    PRODUCT_PRICE_MANDATORY("PRODUCT_008", "Price is mandatory"),
    PRODUCT_PRICE_RANGE_MISMATCH("PRODUCT_009", "Price should be between 1.00 to 99999999.00"),
    PRODUCT_PRINCIPLE_IMAGE_URL_MANDATORY("PRODUCT_010", "Priciple URL is mandatory"),
    PRODUCT_PRINCIPLE_IMAGE_URL_PATTERN_MISMATCH("PRODUCT_011", "Priciple image URL pattern is wrong"),
    PRODUCT_IMAGE_URL_PATTERN_MISMATCH("PRODUCT_011", "Image URL pattern is wrong");

    private String code;
    private String description;


    ExceptionCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExceptionCodes{" +
            "code='" + code + '\'' +
            ", description='" + description + '\'' +
            '}';
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }
}
