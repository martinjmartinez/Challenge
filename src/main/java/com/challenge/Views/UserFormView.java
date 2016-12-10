package com.challenge.Views;

import com.challenge.Model.User;
import com.challenge.Services.UserService;
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
@SpringView(name = UserFormView.VIEW_NAME)
public class UserFormView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "userform";

    @Autowired
    private UserService userService;

    @PostConstruct
    void init() {
        VerticalLayout formContainer = new VerticalLayout();
        VerticalLayout container = new VerticalLayout();
        HorizontalLayout buttonsContainer = new HorizontalLayout();
        TextField name = new TextField("Name");
        TextField lastname = new TextField("LastName");
        TextField email = new TextField("E-mail");
        TextField address = new TextField("Address");
        TextField password = new TextField("Password");
        TextField confirm = new TextField("Confirm Password");
        OptionGroup userType = new OptionGroup("User Type:");
        OptionGroup accountType = new OptionGroup("Account Type:");
        Button addButton = new Button("Add");
        userType.addItems("Generic User", "Sells Departament", "Inventory Departament");
        accountType.addItems("Legal", "Final");

        buttonsContainer.addComponents(addButton);
        formContainer.addComponents(name, lastname, email, userType,address, accountType, password, confirm);
        setSizeUndefined();

        setMargin(true);
        setSpacing(true);

        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addButton.addClickListener((Button.ClickListener) event -> {
            User user =  new User();
            user.setName(name.getValue());
            user.setLastname(lastname.getValue());
            user.setEmail(email.getValue());
            user.setAddress(address.getValue());
            user.setRole(userType.getValue().toString());
            user.setAccountType(accountType.getValue().toString());
            if(password.getValue().equals(confirm.getValue())){
                user.setPassword(password.getValue());
            }

            userService.save(user);

            name.clear();
            lastname.clear();
            address.clear();
            email.clear();
            userType.clear();
            accountType.clear();
            password.clear();
            confirm.clear();


        });

        name.setCaption("Name:");
        lastname.setCaption("LastName:");
        email.setCaption("Email:");
        address.setCaption("Address:");
        password.setCaption("Password:");
        confirm.setCaption("Confirm Password:");
        userType.setCaption("User type:");

        container.addComponents(formContainer, buttonsContainer);
        addComponents(container);

        formContainer.setWidth("100%");
        buttonsContainer.setWidth("100%");
        name.setWidth("100%");
        lastname.setWidth("100%");
        email.setWidth("100%");
        userType.setWidth("100%");
        address.setWidth("100%");
        accountType.setWidth("100%");
        password.setWidth("100%");
        confirm.setWidth("100%");
        container.setSpacing(true);
        container.setMargin(true);

        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

