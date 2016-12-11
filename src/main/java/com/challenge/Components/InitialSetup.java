package com.challenge.Components;

import com.challenge.Model.CartItem;
import com.challenge.Model.Product;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.CartItemService;
import com.challenge.Services.ProductService;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by marti on 8/12/2016.
 */
@Component
public class InitialSetup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    CartItemService cartItemService;

    boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        if(!userService.exists("djmartox")) {
            User admin = new User();
            admin.setName("Martin");
            admin.setLastname("Martinez");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin");
            admin.setRole("Sells Departament");
            admin.setAddress("Calle D, #10, Urb.Casilda");

            Product product = new Product();
            product.setName("iPhone 6");
            product.setPrice(20000);
            product.setDescription("Lo mismo, pero diferente");
            product.setQuantity(10);
            Product product2 = new Product();
            product2.setName("iPhone 7");
            product2.setPrice(25000);
            product2.setDescription("Lo mismo, pero diferente");
            product2.setQuantity(10);
            Product product3 = new Product();
            product3.setName("iPhone 8");
            product3.setPrice(30000);
            product3.setDescription("Lo mismo, pero diferente");
            product3.setQuantity(10);

            userService.save(admin);
            productService.save(product);
            productService.save(product2);
            productService.save(product3);

        }

        alreadySetup = true;
    }
}
