package com.challenge.Services;

import com.challenge.Model.Product;
import com.challenge.Model.User;
import com.challenge.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by martin on 07/12/16.
 */

@Service
public class ProductService {
    @Autowired
    private ProductRepository productoRepository;

    public List<Product> findAll() {
        return (List<Product>) productoRepository.findAll();
    }

    @Transactional
    public Product save(Product product) {
        productoRepository.save(product);
        return product;
    }

    @Transactional
    public List<Product> findByUser(User user) {
        return productoRepository.findByUser(user);
    }

    @Transactional
    public boolean delete(Product product) {
        productoRepository.delete(product);
        return true;
    }

}
