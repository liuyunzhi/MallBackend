package com.thoughtworks.mallbackend.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {

    private Long productId;
    private Long orderId;
    private Integer count;
}
