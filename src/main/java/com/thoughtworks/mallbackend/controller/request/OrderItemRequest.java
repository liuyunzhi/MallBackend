package com.thoughtworks.mallbackend.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {

    private Long productId;
    private Integer count;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Integer count) {
        this.count = count;
    }

    public OrderItemRequest(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
