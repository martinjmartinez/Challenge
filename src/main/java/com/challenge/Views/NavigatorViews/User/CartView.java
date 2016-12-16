package com.challenge.Views.NavigatorViews.User;

import com.challenge.Components.Email;
import com.challenge.Model.CartItem;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.CartItemService;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import com.challenge.Views.Modals.CheckoutNotificationView;
import com.challenge.Views.NavigatorViews.Common.MainUI;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.transport.RestConnection;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.risto.formsender.FormSender;
import org.vaadin.risto.formsender.widgetset.client.shared.Method;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */

@UIScope
@SpringView(name = CartView.VIEW_NAME)
public class CartView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Cart";

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
            if(ci.getInCart()){
                Object newItemId = table.addItem();
                Item row1 = table.getItem(newItemId);

                TextField quantityInput = new TextField();
                quantityInput.setValue(ci.getQuantity().toString());

                row1.getItemProperty("Name").setValue(ci.getProduct().getName());
                row1.getItemProperty("Price").setValue(ci.getProduct().getPrice());
                row1.getItemProperty("Quantity").setValue(quantityInput);


                if(Integer.parseInt(quantityInput.getValue())!= ci.getQuantity()){
                    ci.setQuantity(Integer.parseInt(quantityInput.getValue()));
                    cartItemService.save(ci);
                }
            }

        }

        Button checkOutPayPal = new Button("PayPal");
        checkOutPayPal.addClickListener(new Button.ClickListener() {
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
                receipt.setCantidadItems(CantidadItems);
                receipt.setTotal(total);

                try{
                    emailSender.sendReceiptEmail(receipt, userService);
                }catch (SparkPostException e){
                    System.out.print("Error: " + e);
                }
                receiptService.save(receipt);

                for(CartItem ci : cartItems){
                    ci.setReceipt(receipt);
                    ci.setInCart(false);
                    cartItemService.save(ci);
                }


                FormSender form=new FormSender();
                form.setFormAction("https://www.sandbox.paypal.com/cgi-bin/websc");
                form.setFormMethod(Method.POST);

                //enviando los parametros.
                form.addValue("cmd", "_xclick");
                form.addValue("business", "1000");
                form.addValue("currency_code", "DOP");

                form.addValue("cbt", "Completar proceso de Compra Vaadin");
                form.addValue("rm", "2");
                form.addValue("return", "http://localhost:8080/");
                form.addValue("cancel_return", "http://localhost:8080/");

                form.addValue("invoice", receipt.toString());
                for (CartItem ci : receipt.getCartItems()){
                    form.addValue("item_name", ci.getProduct().getName());
                }
                form.addValue("amount", receipt.getTotal().toString());

                form.extend(getUI());
                form.submit();

                UI.getCurrent().addWindow(new CheckoutNotificationView(receipt));
            }
        });

        addComponents(table, checkOutPayPal);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
