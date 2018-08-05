package com.thoughtworks.mallbackend.controller;

import com.thoughtworks.mallbackend.controller.request.OrderRequest;
import com.thoughtworks.mallbackend.entity.Order;
import com.thoughtworks.mallbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity add(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.add(orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }
}
