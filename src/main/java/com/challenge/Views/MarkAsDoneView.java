package com.challenge.Views;

import com.challenge.Model.Receipt;
import com.challenge.Services.ReceiptService;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */
@UIScope
@SpringView(name = MarkAsDoneView.VIEW_NAME)
public class MarkAsDoneView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "markasdone";

    @Autowired
    ReceiptService receiptService;

    MainUI mainUI = new MainUI();
    @PostConstruct
    void init() {
        mainUI.filter();
        setMargin(true);
        setSpacing(true);
        Table table = new Table("Facturas Para Entregar");
        table.addContainerProperty("ID", Long.class, null);
        table.addContainerProperty("Usuario", String.class, null);
        table.addContainerProperty("Fecha", Date.class, null);
        table.addContainerProperty("Action", Button.class, null);

        table.setSizeFull();

        List<Receipt> receipts = receiptService.findAll();

        for (Receipt r : receipts) {
            if (r.getDelivered())
                continue;
            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);

            Button button = new Button("Entregar");
            button.setData(r);
            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    r.setDelivered(true);
                    receiptService.save(r);
                }
            });

            row1.getItemProperty("ID").setValue(r.getId());
            row1.getItemProperty("Fecha").setValue(new Date());
            row1.getItemProperty("Usuario").setValue(r.getUser().getName());
            row1.getItemProperty("Action").setValue(button);
        }

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
