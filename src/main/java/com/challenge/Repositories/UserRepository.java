package com.challenge.Repositories;


import com.challenge.Model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by martin on 07/12/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRole(String role);
}
