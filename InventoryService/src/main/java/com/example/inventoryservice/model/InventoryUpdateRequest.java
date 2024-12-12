
package com.example.inventoryservice.model;

import java.util.List;

public class InventoryUpdateRequest {
    private List<InventoryItem> items;

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }

    public static class InventoryItem {
        private Long productId;
        private int quantity;

        // Getters and setters
    }
}
