package com.challenge.Views.NavigatorViews.Admin;

import com.challenge.Model.User;
import com.challenge.Services.UserService;
import com.challenge.Views.NavigatorViews.Common.MainUI;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.*;
import com.vaadin.spring.annotation.SpringView;
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
    public static final String VIEW_NAME = "Users";

    @Autowired
    UserService userService;

    MainUI mainUI = new MainUI();

    @PostConstruct
    void init() {
        mainUI.filterPage();
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Todos Los Usuarios");

        table.addContainerProperty("Name",String.class,null);
        table.addContainerProperty("Lastname",String.class,null);
        table.addContainerProperty("Role",String.class,null);
        table.addContainerProperty("Account",String.class,null);
        table.addContainerProperty("Email",String.class,null);
        table.addContainerProperty("Address",String.class,null);

        table.setSizeFull();

        List<User> usuarios = userService.findAll();
        for(User u: usuarios){
            Object newItemId = table.addItem();
            Item row = table.getItem(newItemId);

            row.getItemProperty("Name").setValue(u.getName());
            row.getItemProperty("Lastname").setValue(u.getLastname());
            row.getItemProperty("Role").setValue(u.getRole());
            row.getItemProperty("Account").setValue(u.getAccountType());
            row.getItemProperty("Email").setValue(u.getEmail());
            row.getItemProperty("Address").setValue(u.getAddress());

            //TODO show different users
        }


        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
