package com.challenge.Views.Modals;

import com.challenge.Model.CartItem;
import com.challenge.Model.Product;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.ReceiptService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import com.vaadin.ui.*;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by marti on 10/12/2016.
 */

public class ReceiptView extends Window{

    @Autowired
    ReceiptService receiptService;

    public ReceiptView(Receipt receipt) {
        super("Receipt"); // Set window caption
        center();

        // Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        setWidth("50%");
        setHeight("80%");

        Label total = new Label("Total: ");
        Label date = new Label("Date: ");

        Table table = new Table("Todos Los Productos");
        table.addContainerProperty("Name",String.class,null);
        table.addContainerProperty("Quantity",Integer.class,null);
        table.addContainerProperty("Price",Float.class,null);
        table.setSizeFull();


        List<CartItem> products = receipt.getCartItems();
        for(CartItem p : products){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            row1.getItemProperty("Quantity").setValue(p.getQuantity());
            row1.getItemProperty("Name").setValue(p.getProduct().getName());
            row1.getItemProperty("Price").setValue(p.getProduct().getPrice());

        }

        // Disable the close button
        setClosable(true);
        total.setSizeFull();
        total.setValue(total.getValue() + receipt.getTotal());
        date.setValue(receipt.getDate().toString());
        // Trivial logic for closing the sub-window
        Button ok = new Button("OK");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close(); // Close the sub-window
            }
        });
        content.addComponents(date,table,total,ok);
        setContent(content);
    }
}
