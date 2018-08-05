package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
