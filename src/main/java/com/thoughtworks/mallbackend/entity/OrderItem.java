package com.thoughtworks.mallbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Long orderId;
    private Integer count;

    public OrderItem() {
    }

    public OrderItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public OrderItem(Product product, Long orderId, Integer count) {
        this.product = product;
        this.orderId = orderId;
        this.count = count;
    }
}
