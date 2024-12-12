
package com.example.inventoryservice.service;

import com.example.inventoryservice.model.InventoryUpdateRequest;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void updateInventory(InventoryUpdateRequest request) {
        request.getItems().forEach(item -> {
            int updatedRows = inventoryRepository.decrementStock(item.getProductId(), item.getQuantity());
            if (updatedRows == 0) {
                throw new RuntimeException("Stock not available for product ID: " + item.getProductId());
            }
        });
    }
}
