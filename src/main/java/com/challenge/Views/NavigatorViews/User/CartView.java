package com.challenge.Views.NavigatorViews.User;

import com.challenge.Components.Email;
import com.challenge.Model.CartItem;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.CartItemService;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import com.challenge.Views.NavigatorViews.Common.MainUI;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;
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

    @Autowired
    ReceiptService receiptService;

    MainUI mainUI = new MainUI();

    private Email emailSender = new Email();


    @PostConstruct
    void init() {
        mainUI.filterPage();
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

        Button checkout = new Button("Comprar");

        checkout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                //TODO AFTER RECEIPT MODEL IS DONE

                Date date = new Date();
                Receipt receipt = new Receipt();
                receipt.setDate(date);
                receipt.setCartItems(cartItems);
                receipt.setUser(currentUser);

                float total = 0;
                int CantidadItems = 0;
                for(CartItem ci : cartItems){
                    total = total + ci.getProduct().getPrice() * ci.getQuantity();
                    CantidadItems += ci.getProduct().getQuantity();
                }
                receipt.setTotal(total);
                //TODO UNCOMMENT
                try{
                    emailSender.sendReceiptEmail(receipt);
                }catch (SparkPostException e){
                    System.out.print("Error: " + e);
                }
                receiptService.save(receipt);

                for(CartItem ci : cartItems){
                  ci.setReceipt(receipt);
                  cartItemService.save(ci);

                }
                UI.getCurrent().addWindow(new SuccesView(receipt));
            }
        });
        addComponents(table, checkout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
