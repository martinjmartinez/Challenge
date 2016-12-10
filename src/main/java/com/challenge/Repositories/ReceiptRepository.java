package com.challenge.Repositories;

import com.challenge.Model.Receipt;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Edward on 12/10/2016.
 */
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
