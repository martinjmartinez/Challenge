package com.challenge.Views.NavigatorViews.Common;


import com.challenge.Services.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.*;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;


/**
 * Created by martin on 09/12/16.
 */

@SpringView(name = Index.VIEW_NAME)
public class Index extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @Autowired
    UserService userService;

    @PostConstruct
    void init() {
        addComponent(new Label("This is the default view"));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
