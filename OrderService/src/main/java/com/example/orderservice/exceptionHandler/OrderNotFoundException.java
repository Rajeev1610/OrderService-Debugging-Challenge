package com.example.orderservice.exceptionHandler;



public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message) {
        super(message);
    }

}
