package com.challenge.Services;

import com.challenge.Model.CartItem;
import com.challenge.Model.Product;
import com.challenge.Model.User;
import com.challenge.Repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marti on 10/12/2016.
 */

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> findAll() {
        return (List<CartItem>) cartItemRepository.findAll();
    }

    public CartItem findByUserAndProduct(User user, Product product) {
        return cartItemRepository.findByUserAndProduct(user, product);
    }


    public List<CartItem> findByUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Transactional
    public CartItem save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    @Transactional
    public boolean delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
        return true;
    }

}
