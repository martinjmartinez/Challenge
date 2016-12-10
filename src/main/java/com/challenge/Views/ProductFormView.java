package com.challenge.Views;

import com.challenge.Model.Product;
import com.challenge.Services.ProductService;
import com.vaadin.data.Item;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by marti on 10/12/2016.
 */

@UIScope
@SpringView(name = ProductFormView.VIEW_NAME)
public class ProductFormView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "productform";

    @Autowired
    ProductService productService;

    @PostConstruct
    void init() {
        VerticalLayout formContainer = new VerticalLayout();
        TextField name = new TextField("Name");
        TextField description = new TextField("Description");
        TextField price = new TextField("Price");
        TextField quantity = new TextField("Quantity");
        Button addButton = new Button("Add");

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addButton.addClickListener((Button.ClickListener) event -> {
            Product product = new Product();
            product.setName(name.getValue());
            product.setDescription(description.getValue());
            product.setPrice( Float.parseFloat(price.getValue()));
            product.setQuantity(Integer.parseInt(quantity.getValue()));

            productService.save(product);

            name.clear();
            description.clear();
            price.clear();
            quantity.clear();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponent(addButton);

        formContainer.addComponents(name, description, price, quantity, buttons);

        formContainer.setWidth("100%");
        name.setWidth("100%");
        description.setWidth("100%");
        price.setWidth("100%");
        quantity.setWidth("100%");
        buttons.setWidth("100%");
        formContainer.setSpacing(true);

        name.setCaption("Name:");
        description.setCaption("Description:");
        price.setCaption("Price:");
        quantity.setCaption("Quantity:");

        addComponents(formContainer);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
