package com.challenge.Views.Modals;


import com.challenge.Model.User;
import com.challenge.Services.UserService;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;



/**
 * Created by martin on 12/12/16.
 */@UIScope
public class TaxReceipt extends Window {

    public TaxReceipt(User user, UserService userService) {
        super("Tax Receipt"); // Set window caption
        center();

        // Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        TextField taxReceipt = new TextField("Tax Receipt");
        Button done = new Button("Done");
        content.setMargin(true);
        setWidth("25%");
        setHeight("20%");

        done.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                user.setName("test");
                user.setLastname("test");
                user.setEmail("test");
                user.setAddress("test");
                user.setRole("");
                user.setAccountType("Legal");
                user.setPassword("test");
                user.setTaxReceipt(taxReceipt.getValue());
                userService.save(user);
                close();
            }
        });

        content.addComponents(taxReceipt, done);
        setContent(content);
    }
}
