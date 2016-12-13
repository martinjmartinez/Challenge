package com.challenge.Repositories;

import com.challenge.Model.CartItem;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    List<Receipt> findByUser(User user);
    List<Receipt> findByDelivered(Boolean delivered);
}

