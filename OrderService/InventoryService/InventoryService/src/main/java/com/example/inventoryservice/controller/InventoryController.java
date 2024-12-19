package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.InventoryUpdateRequest;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;


    public InventoryController(InventoryRepository inventoryRepository, InventoryService inventoryService, RestTemplate restTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory!"));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryUpdateRequest request) {
        try {
            inventoryService.updateInventory(request);
            return ResponseEntity.ok("Inventory updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody List<InventoryUpdateRequest.InventoryItem> items){
        try{
            inventoryService.validate(items);
            return ResponseEntity.ok("Inventory validated and updated Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+ e.getMessage());
        }
    }
}
