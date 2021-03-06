package com.thoughtworks.mallbackend.controller;

import com.thoughtworks.mallbackend.config.JwtUserDetails;
import com.thoughtworks.mallbackend.controller.request.OrderRequest;
import com.thoughtworks.mallbackend.controller.response.OrderResponse;
import com.thoughtworks.mallbackend.entity.Order;
import com.thoughtworks.mallbackend.exception.OrderNotFoundException;
import com.thoughtworks.mallbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private final JwtUserDetails user = (JwtUserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();

    @PostMapping
    public ResponseEntity add(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.add(user, orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Stream<OrderResponse>> getAll() {
        return ResponseEntity.ok(orderService.getAll(user)
                .stream().map(OrderResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void exceptionHandler(OrderNotFoundException exception) {
    }
}
