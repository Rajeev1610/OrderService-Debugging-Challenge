package com.example.orderservice.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletRequest request){
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp:", LocalDateTime.now());
        response.put("status:",HttpStatus.NOT_FOUND.value());
        response.put("error:","No Orders for this id");
        response.put("path:",request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
