package com.thoughtworks.mallbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private List<OrderItem> orderItems;

    @Transient
    private Double totalPrice;

    public Order() {
    }

    public Order(List<OrderItem> orderItems) {
        this.createDate = new Date();
        this.orderItems = orderItems;
    }
}
