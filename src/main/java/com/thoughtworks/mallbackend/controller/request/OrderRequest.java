package com.thoughtworks.mallbackend.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long userId;
    private List<OrderItemRequest> orderItems;

    public OrderRequest() {
    }

    public OrderRequest(Long userId, List<OrderItemRequest> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
