package com.challenge.Views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by Edward on 12/10/2016.
 */
@UIScope
@SpringView(name = MarkAsDoneView.VIEW_NAME)
public class MarkAsDoneView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "markasdone";

    @PostConstruct
    void init(){

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
