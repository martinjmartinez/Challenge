package com.challenge.Views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.*;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by martin on 09/12/16.
 */
@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "users";

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a view scoped view"));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
