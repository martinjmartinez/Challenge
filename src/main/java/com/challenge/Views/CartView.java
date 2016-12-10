package com.challenge.Views;

import com.challenge.Model.CartItem;
import com.challenge.Model.User;
import com.challenge.Services.CartItemService;
import com.challenge.Services.UserService;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */

@UIScope
@SpringView(name = CartView.VIEW_NAME)
public class CartView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "cart";

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Your Shopping Cart");
        table.addContainerProperty("Name",String.class,null);
        table.addContainerProperty("Price",Float.class,null);
        table.addContainerProperty("Quantity", TextField.class,null);

        table.setSizeFull();

        User currentUser = userService.getCurrentUser();
        List<CartItem> cartItems = cartItemService.findByUser(currentUser);
        for(CartItem ci : cartItems){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            TextField quantityInput = new TextField();
            quantityInput.setValue(ci.getQuantity().toString());

            row1.getItemProperty("Name").setValue(ci.getProduct().getName());
            row1.getItemProperty("Price").setValue(ci.getProduct().getPrice());
            row1.getItemProperty("Quantity").setValue(quantityInput);

            ci.setQuantity(Integer.parseInt(quantityInput.getValue()));
            if(Integer.parseInt(quantityInput.getValue())!= ci.getQuantity()){
                cartItemService.save(ci);
            }

        }

        Button save = new Button("Comprar");

        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                
            }
        });
        addComponents(table, save);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
