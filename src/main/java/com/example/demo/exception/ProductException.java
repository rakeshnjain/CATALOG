package com.example.demo.exception;

public class ProductException extends RuntimeException {

    private static final long serialVersionUID = -7530593011134270615L;

    private ExceptionCodes errorCode;
    private java.lang.String errorMessage;
    private int responseStatusCode;

    public ProductException(
            ExceptionCodes errorCode, String errorMessage,
            int responseStatusCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.responseStatusCode = responseStatusCode;
    }
}
