package com.challenge.Views;

import com.challenge.Model.User;
import com.challenge.Services.UserService;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.*;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by martin on 09/12/16.
 */
@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "users";

    @Autowired
    UserService userService;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Todos Los Usuarios");

        table.addContainerProperty("Nombre",String.class,null);

        List<User> usuarios = userService.findAll();
        for(User u: usuarios){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);
            row1.getItemProperty("Nombre").setValue(u.getName());
            //TODO show different users
        }


        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
