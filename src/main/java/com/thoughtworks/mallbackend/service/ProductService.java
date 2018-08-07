package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.entity.Product;
import com.thoughtworks.mallbackend.exception.ProductNotFoundException;
import com.thoughtworks.mallbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Long add(Product product) {
        return productRepository.save(product).getId();
    }


    public void update(Long id, Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setUnit(product.getUnit());
        oldProduct.setImage(product.getImage());
        oldProduct.setDescription(product.getDescription());
        productRepository.save(oldProduct);
    }
}
