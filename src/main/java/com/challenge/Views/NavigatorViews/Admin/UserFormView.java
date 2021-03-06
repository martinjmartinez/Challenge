package com.challenge.Views.NavigatorViews.Admin;

import com.challenge.Components.Email;
import com.challenge.Model.User;
import com.challenge.Services.UserService;
import com.challenge.Views.Modals.CheckoutNotificationView;
import com.challenge.Views.Modals.TaxReceipt;
import com.challenge.Views.NavigatorViews.Common.MainUI;
import com.sparkpost.exception.SparkPostException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
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
    public static final String VIEW_NAME = "Create_User";

    @Autowired
    private UserService userService;

    MainUI mainUI = new MainUI();
    private Email emailSender = new Email();

    @PostConstruct
    void init() {
        mainUI.filterPage();
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

        formContainer.addComponents(name, lastname, email, userType,address, accountType, password, confirm);

        buttonsContainer.addComponents(addButton);
        setSizeUndefined();

        setMargin(true);
        setSpacing(true);
        User user =  new User();
        accountType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(accountType.getValue().toString().equals("Legal")){
                    UI.getCurrent().addWindow(new TaxReceipt(user, userService));
                }
            }
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addButton.addClickListener((Button.ClickListener) event -> {
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

            try{
                emailSender.sendUserEmail(user);
            }catch (SparkPostException e){
                System.out.print("Error: " + e);
            }


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

