package com.example.orderservice.service;

import com.example.orderservice.exceptionHandler.OrderNotFoundException;
import com.example.orderservice.model.InventoryUpdateRequest;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.Item;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;



    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate ;
    }



    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        if(order.getCustomerName() ==null || order.getCustomerName().isEmpty()){
            throw new IllegalArgumentException("Name can not be empty");
        }
        order = orderRepository.save(order);

        List<Item> items = orderRequest.getItems();
        for (Item item : items) {
            validateItem(item);
            item.setOrderId(order.getId());
            itemRepository.save(item);
        }

        List<InventoryUpdateRequest.InventoryItem> inventoryItems = orderRequest.getInventoryUpdateRequest();
        if (inventoryItems == null || inventoryItems.isEmpty()){
            throw new IllegalArgumentException("Inventory update request cannot be empty");
        }
        validateAndDeductInventory(inventoryItems);
    }

    private void validateAndDeductInventory(List<InventoryUpdateRequest.InventoryItem> inventoryUpdateRequest){
        String url = "http://localhost:8082"+"/validate";
        try{
           ResponseEntity<String> response = restTemplate.postForEntity(url,inventoryUpdateRequest,String.class);
           if(!response.getStatusCode().is2xxSuccessful()){
               throw new RuntimeException("Failed to validate inventory:"+response.getBody());
           }
        }catch (Exception e){
            throw new RuntimeException("Failed to update inventory:"+e.getMessage(),e);
        }

    }

    private void validateItem(Item item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
    }

    public Order getOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }
}