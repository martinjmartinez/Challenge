package com.challenge.Views.NavigatorViews.Admin;

import com.challenge.Model.Receipt;
import com.challenge.Services.ReceiptService;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Edward on 12/12/2016.
 */
@UIScope
@SpringView(name = ChartsView.VIEW_NAME)
public class ChartsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Graficos";

    @Autowired
    ReceiptService receiptService;


    Chart chart = new Chart(ChartType.COLUMN);
    Configuration conf = chart.getConfiguration();
    XAxis x = new XAxis();
    void init() {
        addComponent(chart);
        UpdateChart();
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UpdateChart();
        addComponent(chart);
    }

    public void UpdateChart(){
        List<Receipt> receiptList = receiptService.findAll();
        conf.setTitle("Resumen del dia");

        int i = 0;
        int j = 0;
        int k = 0;
        for(Receipt r: receiptList){
            i+=1;
            if(r.getDelivered()) {
                j += 1;
                continue;
            }
            k+=1;
        }
        conf.addSeries(new ListSeries("Today",i,j,k));
    }

    public void CreateChart(){
        List<Receipt> receiptList = receiptService.findAll();
        conf.setTitle("Resumen del dia");

        x.setCategories("Compras Realizadas","Despachos Pendientes","Despachos Realizados");
        conf.addxAxis(x);
        int i = 0;
        int j = 0;
        int k = 0;
        for(Receipt r: receiptList){
            i+=1;
            if(r.getDelivered()) {
                j += 1;
                continue;
            }
            k+=1;
        }
        conf.addSeries(new ListSeries("Today",i,j,k));
    }
}
