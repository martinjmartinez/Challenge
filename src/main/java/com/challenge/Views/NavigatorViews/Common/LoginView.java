package com.challenge.Views.NavigatorViews.Common;

import com.challenge.Model.User;
import com.challenge.Services.UserService;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;


/**
 * Created by marti on 10/12/2016.
 */

@UIScope
@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Login";

    @Autowired
    private UserService userService;

    private User user;

    @PostConstruct
    void init() {
        Panel wrapper = new Panel();
        VerticalLayout content = new VerticalLayout();
        TextField usernameField = new TextField("E-mail");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");
        Label tittle = new Label("Log in");

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String email = usernameField.getValue();
                System.out.println("Email: " + email);
                String password = passwordField.getValue();
                System.out.println("Pass: " + password);
                user = userService.findByEmail(email);
                if (user != null) {
                    if (user.getPassword().equals(password)) {
                        saveUserInSession(LoginView.this, user);
                    } else {
                        showErrorMessage("The password is incorrect.");
                    }
                } else {
                    showErrorMessage("The user requested doesn't exists.");
                }
            }
        });

        usernameField.setRequired(true);
        usernameField.setCaption("E-mail:");
        usernameField.addValidator(new EmailValidator("Username must be an E-mail address."));
        passwordField.setRequired(true);
        passwordField.setCaption("Password:");
        tittle.setStyleName("h1");
        tittle.setWidth(null);
        content.addComponents(tittle,usernameField,passwordField,loginButton);
        wrapper.setContent(content);
        content.setComponentAlignment(usernameField, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(tittle, Alignment.MIDDLE_CENTER);

        addComponents(content);
    }

    private void saveUserInSession(LoginView loginView, User user) {
        loginView.user = user;

        loginView.getSession().setAttribute("current_user", user);
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("current_user", user);
        getUI().getPage().setLocation("/");
    }

    private void showErrorMessage(String message) {

        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
