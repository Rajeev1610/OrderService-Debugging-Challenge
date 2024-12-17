package com.example.orderservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public class OrderRequest {

    @Id
    @GeneratedValue
    private Long id;

    private String customerName;
    private List<Item> items;

    private List<InventoryUpdateRequest.InventoryItem> inventoryUpdateRequest;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InventoryUpdateRequest.InventoryItem> getInventoryUpdateRequest() {
        return inventoryUpdateRequest;
    }

    public void setInventoryUpdateRequest(List<InventoryUpdateRequest.InventoryItem> inventoryUpdateRequest) {
        this.inventoryUpdateRequest = inventoryUpdateRequest;
    }

}