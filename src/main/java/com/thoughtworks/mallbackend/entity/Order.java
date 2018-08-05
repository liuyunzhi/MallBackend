package com.thoughtworks.mallbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createDate;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(List<OrderItem> orderItems) {
        this.createDate = new Date();
        this.orderItems = orderItems;
    }
}
