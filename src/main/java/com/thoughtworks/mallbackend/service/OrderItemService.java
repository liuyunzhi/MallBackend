package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.entity.OrderItem;
import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.exception.OrderItemNotFoundException;
import com.thoughtworks.mallbackend.repository.OrderItemRepository;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderItem add(OrderItemRequest orderItemRequest) {
        Product product = productRepository.findById(orderItemRequest.getProductId()).orElse(null);
        return orderItemRepository.save(new OrderItem(product, orderItemRequest.getOrderId(), orderItemRequest.getCount()));
    }
}
