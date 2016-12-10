package com.challenge.Repositories;

import com.challenge.Model.CartItem;
import com.challenge.Model.Product;
import com.challenge.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by marti on 10/12/2016.
 */
public interface CartItemRepository  extends CrudRepository<CartItem, Long> {

    CartItem findByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
