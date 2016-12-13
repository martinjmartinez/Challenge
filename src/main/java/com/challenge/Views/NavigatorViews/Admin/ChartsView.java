package com.challenge.Views.NavigatorViews.Admin;

import com.challenge.Model.Receipt;
import com.challenge.Services.ReceiptService;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by marti on 12/12/2016.
 */

@UIScope
@SpringView(name = ChartsView.VIEW_NAME)
public class ChartsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "Charts";

    @Autowired
    ReceiptService receiptService;

    DataSeriesItem allOrders;
    DataSeriesItem queueOrders;
    DataSeriesItem completeOrders;
    DataSeries dataSeries;
    Integer[] databaseResult = new Integer[3];
    Chart chart;
    Configuration chartConfiguration;
    VerticalLayout layout;
    @PostConstruct
    void init() {
        layout = new VerticalLayout();
        layout.setMargin(true);

        chart = new Chart(ChartType.BAR);
        chartConfiguration = chart.getConfiguration();
        chartConfiguration.setTitle("Orders");

        dataSeries = new DataSeries("Orders");

        Integer[] chartData = dataFormat(receiptService.findAll());

        allOrders = new DataSeriesItem("All Orders", chartData[0]);
        queueOrders = new DataSeriesItem("Queue Orders", chartData[1]);
        completeOrders = new DataSeriesItem("Complete Orders", chartData[2]);

        XAxis xaxis = new XAxis();
        xaxis.setCategories("All Orders", "Queue Orders", "Complete Orders");
        chartConfiguration.addxAxis(xaxis);

        dataSeries.add(allOrders);
        dataSeries.add(queueOrders);
        dataSeries.add(completeOrders);


        chartConfiguration.addSeries(dataSeries);
        layout.addComponent(chart);
        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


    }

   public Integer[] dataFormat (List<Receipt> receiptList){
       receiptList =receiptService.findAll();
       Integer[] data = new Integer[3];
       Integer allOrders = receiptList.size();
       Integer queueOrders = 0;
       Integer completeOrders = 0;

       for(Receipt receipt : receiptList){
           if(receipt.getDelivered()){
               completeOrders++;
           }else{
               queueOrders++;
           }
       }

       data[0] = allOrders;
       data[1] = queueOrders;
       data[2] = completeOrders;

       return data;
   }
}
