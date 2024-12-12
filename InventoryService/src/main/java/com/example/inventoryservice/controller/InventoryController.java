
package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.InventoryUpdateRequest;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
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
}
