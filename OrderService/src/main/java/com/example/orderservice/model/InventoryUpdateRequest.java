
package com.example.orderservice.model;

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

        private Long quantity;

        public InventoryItem(Long productId, long quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
    }
}