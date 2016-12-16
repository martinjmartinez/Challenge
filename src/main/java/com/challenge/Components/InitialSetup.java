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

        if(!userService.exists("martin26_8@hotmail.com")) {

            User admin = new User();
            admin.setName("Martin");
            admin.setLastname("Martinez");
            admin.setEmail("martoxgames@gmail.com");
            admin.setPassword("admin");
            admin.setRole("Inventory Departament");
            admin.setAddress("Calle D, #10, Urb.Casilda");
            admin.setAccountType("Final");

            userService.save(admin);

            User admin2 = new User();
            admin2.setName("Manuel");
            admin2.setLastname("Grullon");
            admin2.setEmail("djmartox2@gmail.com");
            admin2.setPassword("admin");
            admin2.setRole("Sells Departament");
            admin2.setAddress("Calle B, #1, Villa Olga");
            admin2.setAccountType("Final");

            userService.save(admin2);

            User admin3 = new User();
            admin3.setName("Gabriela");
            admin3.setLastname("Perez");
            admin3.setEmail("majamacu@gmail.com");
            admin3.setPassword("admin");
            admin3.setRole("Sells Departament");
            admin3.setAddress("Calle Mexico, #3B, Los Jardines");
            admin3.setAccountType("Final");

            userService.save(admin3);

            User admin4 = new User();
            admin4.setName("Juan");
            admin4.setLastname("Gutierrez");
            admin4.setEmail("martin26_8@me.com");
            admin4.setPassword("admin");
            admin4.setRole("Generic User");
            admin4.setAddress("Calle Argentina, #7, Las Carmelitas");
            admin4.setAccountType("Legal");
            admin4.setTaxReceipt("65213254789652314");

            userService.save(admin4);


            Product product = new Product();
            product.setName("iPhone 6");
            product.setPrice(20000);
            product.setDescription("Phone is a smartphone made by Apple that combines an iPod, a tablet PC, a digital camera and a cellular phone.");
            product.setQuantity(50);

            productService.save(product);

            Product product2 = new Product();
            product2.setName("Sansung Galaxy 6");
            product2.setPrice(25000);
            product2.setDescription("Samsung Galaxy is Samsung Electronics' flagship line of Android smartphones and tablets.");
            product2.setQuantity(30);

            productService.save(product2);

            Product product3 = new Product();
            product3.setName("iPhone 6 Blue Cover");
            product3.setPrice(500);
            product3.setDescription("Blue cover for your new iPhone");
            product3.setQuantity(200);

            productService.save(product3);

            Product product4 = new Product();
            product4.setName("Sansung Galaxy 6 Black Cover");
            product4.setPrice(500);
            product4.setDescription("Blue cover for your new Galaxy");
            product4.setQuantity(200);

            productService.save(product4);


            Product product5 = new Product();
            product5.setName("iPhone 6 Black Cover");
            product5.setPrice(500);
            product5.setDescription("Blue cover for your new iPhone");
            product5.setQuantity(200);

            productService.save(product5);

            Product product6 = new Product();
            product6.setName("Sansung Galaxy 6 Gray Cover");
            product6.setPrice(500);
            product6.setDescription("Blue cover for your new Galaxy");
            product6.setQuantity(200);

            productService.save(product6);





        }

        alreadySetup = true;
    }
}
