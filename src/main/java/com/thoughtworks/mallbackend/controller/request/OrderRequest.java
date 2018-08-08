package com.thoughtworks.mallbackend.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<OrderItemRequest> orderItems;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
