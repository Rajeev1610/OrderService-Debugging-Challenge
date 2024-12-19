
package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.InventoryUpdateRequest;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final RestTemplate restTemplate;

    private final InventoryRepository inventoryRepository;

    public InventoryService(RestTemplate restTemplate, InventoryRepository inventoryRepository) {
        this.restTemplate = restTemplate;
        this.inventoryRepository = inventoryRepository;
    }
    public void updateInventory(InventoryUpdateRequest request) {
        request.getItems().forEach(item -> {
            Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(item.getProductId());
            if (inventoryOptional.isPresent()){
                Inventory inventory1 = inventoryOptional.get();
                inventory1.setProductId(item.getProductId());
                inventory1.setQuantity(item.getQuantity());
                inventoryRepository.save(inventory1);
            }else {
                Inventory in = new Inventory();
                in.setProductId(item.getProductId());
                in.setQuantity(item.getQuantity());
                inventoryRepository.save(in);
            }
        });
    }
    @Transactional
   public void validate(List<InventoryUpdateRequest.InventoryItem> items){
        for(InventoryUpdateRequest.InventoryItem item : items){
            Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(item.getProductId());
            if(inventoryOptional.isPresent()){
                Inventory inventory = inventoryOptional.get();
                if(inventory.getQuantity() < item.getQuantity()){
                    throw new IllegalStateException(
                            "Insufficient inventory for product id:"+item.getProductId()+
                                    "  Available:"+inventory.getQuantity()
                    );

                }
                inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
                inventoryRepository.save(inventory);
            }else{
                throw new IllegalStateException("ProductId:"+ item.getProductId() + " Quantity:"+item.getQuantity());
            }
        }
   }
}