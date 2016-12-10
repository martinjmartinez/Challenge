package com.challenge.Views;

import com.challenge.Model.CartItem;
import com.challenge.Model.Receipt;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
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
@SpringView(name = ReceiptsView.VIEW_NAME)
public class ReceiptsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "receipts";
    @Autowired
    ReceiptService receiptService;

    @Autowired
    UserService userService;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Todos Las Facturas");
        table.addContainerProperty("ID",Long.class,null);
        table.addContainerProperty("Usuario",String .class,null);
        table.addContainerProperty("Fecha",Date.class,null);
        table.addContainerProperty("Entregada", Boolean.class,null);
        table.addContainerProperty("Action", Button.class,null);

        table.setSizeFull();

        List<Receipt> receipts = receiptService.findAll();
        for (Receipt r: receipts){
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            Button receiptInfoBtn = new Button("Ver Detalles");
            receiptInfoBtn.setData(r);
            List<CartItem> asd = r.getCartItems();
            receiptInfoBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    UI.getCurrent().addWindow(new ReceiptView(r));
                }
            });
            row1.getItemProperty("ID").setValue(r.getId());
            row1.getItemProperty("Fecha").setValue(new Date());
            row1.getItemProperty("Usuario").setValue(r.getUser().getName());
            row1.getItemProperty("Entregada").setValue(r.getDelivered());
            row1.getItemProperty("Action").setValue(receiptInfoBtn);
        }

        addComponent(table);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }


}
