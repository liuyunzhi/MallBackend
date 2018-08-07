package com.thoughtworks.mallbackend.controller.response;

import com.thoughtworks.mallbackend.entity.Order;
import com.thoughtworks.mallbackend.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private Date createDate;
    private List<OrderItem> orderItems;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.createDate = order.getCreateDate();
        this.orderItems = order.getOrderItems();
    }
}
