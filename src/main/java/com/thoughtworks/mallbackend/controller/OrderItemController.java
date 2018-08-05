package com.thoughtworks.mallbackend.controller;

import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.entity.OrderItem;
import com.thoughtworks.mallbackend.exception.OrderItemNotFoundException;
import com.thoughtworks.mallbackend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity add(@RequestBody OrderItemRequest orderItemRequest) {
        OrderItem orderItem = orderItemService.add(orderItemRequest);
        return ResponseEntity.created(URI.create("/orderItems/" + orderItem.getId())).build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void exceptionHandler(OrderItemNotFoundException exception) {}
}