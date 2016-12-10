package com.challenge.Views;

import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by Edward on 12/10/2016.
 */

@UIScope
@SpringView(name = CartView.VIEW_NAME)
public class CartView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "cart";
    //TODO Get the current user cart
    @PostConstruct
    void init() {
        Table table = new Table("Carro de Compras");
        table.addContainerProperty("Nombre",String.class,null);
        table.addContainerProperty("Text", TextField.class,null);

        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);
        TextField text = new TextField();
        row1.getItemProperty("Nombre").setValue("Test");
        row1.getItemProperty("Text").setValue(text);
        Button save = new Button("Comprar");
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
            }
        });
        addComponent(table);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
