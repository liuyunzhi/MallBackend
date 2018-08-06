package com.thoughtworks.mallbackend.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {

    private Long productId;
    private Long orderId;
    private Integer count;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Integer count) {
        this.count = count;
    }

    public OrderItemRequest(Long productId, Long orderId, Integer count) {
        this.productId = productId;
        this.orderId = orderId;
        this.count = count;
    }
}
