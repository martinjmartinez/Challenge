package com.challenge.Views.NavigatorViews.Common;

import com.challenge.Model.CartItem;
import com.challenge.Model.Product;
import com.challenge.Model.User;
import com.challenge.Services.CartItemService;
import com.challenge.Services.ProductService;
import com.challenge.Services.UserService;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.*;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by martin on 09/12/16.
 */
@UIScope
@SpringView(name = ProductsView.VIEW_NAME)
public class ProductsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Products";

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    MainUI mainUI = new MainUI();

    @PostConstruct
    void init() {
        mainUI.filterPage();
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Todos Los Productos");
        table.addContainerProperty("Name",String.class,null);
        table.addContainerProperty("Description",String .class,null);
        table.addContainerProperty("Quantity available",Integer.class,null);
        table.addContainerProperty("Price",Float.class,null);
        table.addContainerProperty("Action", Button.class,null);

        table.setSizeFull();

        List<Product> productos = productService.findAll();
        for (Product p: productos){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            Button button = new Button("Añadir al carrito");
            button.setData(p);
            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    User currentUser = userService.getCurrentUser();

                    if(cartItemService.findByUserAndProduct(currentUser, p)!=null){
                        CartItem cartItem = cartItemService.findByUserAndProduct(currentUser, p);
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        cartItemService.save(cartItem);

                    }else{
                        CartItem cartItem = new CartItem();
                        cartItem.setProduct(p);
                        cartItem.setQuantity(1);
                        cartItem.setUser(currentUser);
                        cartItemService.save(cartItem);
                    }
                }
            });

            row1.getItemProperty("Quantity available").setValue(p.getQuantity());
            row1.getItemProperty("Name").setValue(p.getName());
            row1.getItemProperty("Description").setValue(p.getDescription());
            row1.getItemProperty("Price").setValue(p.getPrice());
            row1.getItemProperty("Action").setValue(button);
        }

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

}
