package com.challenge.Repositories;

import com.challenge.Model.Product;
import com.challenge.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by martin on 07/12/16.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByUser(User user);

}