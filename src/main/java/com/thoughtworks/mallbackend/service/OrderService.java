package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.config.JwtUserDetails;
import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.controller.request.OrderRequest;
import com.thoughtworks.mallbackend.entity.Order;
import com.thoughtworks.mallbackend.entity.OrderItem;
import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.entity.Users;
import com.thoughtworks.mallbackend.exception.OrderNotFoundException;
import com.thoughtworks.mallbackend.repository.OrderItemRepository;
import com.thoughtworks.mallbackend.repository.OrderRepository;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import com.thoughtworks.mallbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Order add(JwtUserDetails userDetails, OrderRequest orderRequest) {
        Users user = usersRepository.findById(userDetails.getId()).orElse(null);
        Order order = new Order(user, new Date());
        orderRepository.save(order);
        mapRequestToEntity(orderRequest.getOrderItems()).forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
            orderItemRepository.save(orderItem);
        });
        return order;
    }

    public List<Order> getAll(JwtUserDetails user) {

        return orderRepository.findByUserId(user.getId());
    }

    public Order get(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    private List<OrderItem> mapRequestToEntity(List<OrderItemRequest> orderItemRequests) {
        return orderItemRequests.stream().map(orderItemRequest -> {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElse(null);
            return new OrderItem(product, orderItemRequest.getCount());
        }).collect(Collectors.toList());
    }
}
