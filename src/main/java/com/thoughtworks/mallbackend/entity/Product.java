package com.thoughtworks.mallbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String unit;
    private String image;
    private String description;
    private Date productionDate;
    private String productionPlace;

    public Product() {
    }

    public Product(String name, Double price, String unit, String image, String description, Date productionDate, String productionPlace) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.image = image;
        this.description = description;
        this.productionDate = productionDate;
        this.productionPlace = productionPlace;
    }
}
