package com.challenge.Repositories;


import com.challenge.Model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by martin on 07/12/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
