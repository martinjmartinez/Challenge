package com.challenge.Views;

import com.challenge.Model.Product;
import com.challenge.Services.ProductService;
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
    public static final String VIEW_NAME = "products";

    @Autowired
    private ProductService productService;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Todos Los Productos");
        table.addContainerProperty("Nombre",String.class,null);
        table.addContainerProperty("Cantidad",Integer.class,null);
        table.addContainerProperty("Precio",Float.class,null);
        table.addContainerProperty("boton", Button.class,null);

        table.setSizeFull();

        for(int i = 0; i < 3; i++){
            Product p = new Product();
            p.setDescription("asdf");
            p.setName("asdf");
            p.setQuantity(i);
            p.setPrice((float)i);
            productService.save(p);
        }
        List<Product> productos = productService.findAll();
        for (Product p: productos){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            Button button = new Button("Añadir al carrito");
            button.setData(p);
            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Product product = (Product)event.getButton().getData();

                }
            });

            row1.getItemProperty("Cantidad").setValue(p.getQuantity());
            row1.getItemProperty("Nombre").setValue(p.getName());
            row1.getItemProperty("Precio").setValue(p.getPrice());
            row1.getItemProperty("boton").setValue(button);
        }

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

}
