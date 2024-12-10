package com.example.orderservice.service;

import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.Item;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order = orderRepository.save(order);

        List<Item> items = orderRequest.getItems();
        for (Item item : items) {
            validateItem(item);
            item.setOrderId(order.getId());
            itemRepository.save(item);
        }
    }

    private void validateItem(Item item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}