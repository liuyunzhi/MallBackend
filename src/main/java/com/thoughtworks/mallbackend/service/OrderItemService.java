package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.entity.OrderItem;
import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.exception.OrderItemNotFoundException;
import com.thoughtworks.mallbackend.repository.OrderItemRepository;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderItem add(Long orderId, OrderItemRequest orderItemRequest) {
        Optional<OrderItem> optional = orderItemRepository.findByProductIdAndOrderId(
                orderItemRequest.getProductId(),
                orderId);
        if (optional.isPresent()) {
            OrderItem oldOrderItem = optional.get();
            oldOrderItem.setCount(oldOrderItem.getCount() + orderItemRequest.getCount());
            return orderItemRepository.save(oldOrderItem);
        } else {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElse(null);
            return orderItemRepository.save(
                    new OrderItem(product, orderId, orderItemRequest.getCount()));
        }
    }

    public void update(Long id, OrderItemRequest orderItemRequest) {
        OrderItem oldOrderItem = orderItemRepository.findById(id)
                .orElseThrow(OrderItemNotFoundException::new);
        oldOrderItem.setCount(orderItemRequest.getCount());
        orderItemRepository.save(oldOrderItem);
    }

    public void remove(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        orderItemRepository.delete(orderItem);
    }
}
