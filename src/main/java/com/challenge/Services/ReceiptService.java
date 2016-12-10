package com.challenge.Services;

import com.challenge.Model.Product;
import com.challenge.Model.Receipt;
import com.challenge.Repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    public List<Receipt> findAll(){return (List<Receipt>) receiptRepository.findAll();}
}
