package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.controller.request.OrderRequest;
import com.thoughtworks.mallbackend.entity.Order;
import com.thoughtworks.mallbackend.entity.OrderItem;
import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.repository.OrderItemRepository;
import com.thoughtworks.mallbackend.repository.OrderRepository;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order add(OrderRequest orderRequest) {
        List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItems();
        List<OrderItem> orderItems = orderItemRequests.stream().map(orderItemRequest -> {
            Product product = productRepository.findById(orderItemRequest.getProductId()).orElse(null);
            return new OrderItem(product, orderItemRequest.getCount());
        }).collect(Collectors.toList());
        Order order = orderRepository.save(new Order(orderItems));
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
            orderItemRepository.save(orderItem);
        });
        return order;
    }
}
