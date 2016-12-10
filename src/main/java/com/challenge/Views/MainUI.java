package com.challenge.Views;

import com.challenge.Model.User;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by martin on 09/12/16.
 */
@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay{

    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {


        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);



        //TODO filter for user rol, easy enough, just make an if that contains all of the administrative buttons
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        navigationBar.addComponent(createNavigationButton("Products",ProductsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Users",UsersView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Cart",CartView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Add User",UserFormView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Add Product",ProductFormView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Todas Las Ventas",ReceiptsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Facturas Para Entregar",MarkAsDoneView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Historial de Compras",UserReceiptView.VIEW_NAME));

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();


        User currentUser = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("current_user");

        if (currentUser == null) {
            getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
            root.addComponent(springViewDisplay);
            root.setExpandRatio(springViewDisplay, 1.0f);
            setContent(root);
        }else {
            root.addComponent(navigationBar);
            root.addComponent(springViewDisplay);
            root.setExpandRatio(springViewDisplay, 1.0f);
            setContent(root);
        }
    }
    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this
        // to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }

}
