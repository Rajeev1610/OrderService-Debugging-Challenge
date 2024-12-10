package com.example.orderservice.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<Item> items;

    // Getters and setters
}