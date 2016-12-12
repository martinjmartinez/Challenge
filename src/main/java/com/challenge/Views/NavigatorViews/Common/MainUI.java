package com.challenge.Views.NavigatorViews.Common;

import com.challenge.Model.User;
import com.challenge.Views.NavigatorViews.Admin.*;
import com.challenge.Views.NavigatorViews.User.CartView;
import com.challenge.Views.NavigatorViews.User.OrdersRecordView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.exsio.plupload.PluploadReceiver;

/**
 * Created by martin on 09/12/16.
 */
@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay{

    private Panel springViewDisplay;
    private VerticalLayout root = new VerticalLayout();
    private CssLayout navigationBar = new CssLayout();
    @Override
    protected void init(VaadinRequest request) {
        Button logout = new Button("Logout");
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);

        //TODO filter for user rol, easy enough, just make an if that contains all of the administrative buttons
        User currentUser = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("current_user");
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        if(currentUser == null){

        }else{
            if(currentUser.isAdmin()){
                navigationBar.addComponent(createNavigationButton("Products", ProductsView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Users", UsersView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Add User", UserFormView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Add Product", ProductFormView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Todas Las Ventas", OrdersView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Facturas Para Entregar", OpenOrdersView.VIEW_NAME));
                navigationBar.addComponent(logout);
            }else{
                navigationBar.addComponent(createNavigationButton("Products",ProductsView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Cart", CartView.VIEW_NAME));
                navigationBar.addComponent(createNavigationButton("Historial de Compras", OrdersRecordView.VIEW_NAME));
                navigationBar.addComponent(logout);
            }
        }


        logout.addStyleName(ValoTheme.BUTTON_SMALL);
        logout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("current_user");
                getUI().getPage().setLocation("/");
            }
        });

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        filter();
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

    public void filter(){
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

    public void filterPage(){
        User currentUser = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("current_user");

        if (currentUser == null) {
            getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
        }else {

        }
    }

}
