package com.challenge.Services;

import com.challenge.Model.Product;
import com.challenge.Model.Receipt;
import com.challenge.Repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */
@Service
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    public List<Receipt> findAll(){return (List<Receipt>) receiptRepository.findAll();}

    @Transactional
    public Receipt save(Receipt receipt) {
        receiptRepository.save(receipt);
        return receipt;
    }

}
