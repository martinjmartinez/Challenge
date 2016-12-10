package com.challenge.Views;

import com.challenge.Model.Product;
import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.ReceiptService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import com.vaadin.ui.*;
import java.util.List;
import com.vaadin.data.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Edward on 12/10/2016.
 */
@UIScope
@SpringView(name = UserReceiptView.VIEW_NAME)
public class UserReceiptView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Userreceipts";
    @Autowired
    ReceiptService receiptService;

    MainUI mainUI = new MainUI();
    @PostConstruct
    void init() {
        mainUI.filter();
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Historial De Compras");
        table.addContainerProperty("Fecha",Date.class,null);
        table.addContainerProperty("Entregada", Boolean.class,null);
        table.addContainerProperty("Action", Button.class,null);

        table.setSizeFull();
        User currentUser = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("current_user");
        List<Receipt> receipts = receiptService.findAll();

        for (Receipt r: receipts){
            if(r.getUser().getId() != currentUser.getId())
                continue;
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            Button button = new Button("Ver Detalles");
            button.setData(r);
            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    UI.getCurrent().addWindow(new ReceiptView(r));
                }
            });

            row1.getItemProperty("Fecha").setValue(new Date());
            row1.getItemProperty("Entregada").setValue(r.getDelivered());
            row1.getItemProperty("Action").setValue(button);
        }

        addComponent(table);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
