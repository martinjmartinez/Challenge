package com.challenge.Components;

import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by marti on 10/12/2016.
 */

@Component
public class Email {

    String API_KEY = "246b63f0891f476f64bde108861bd5a9e1ecb900";
    Client client = new Client(API_KEY);
    public void sendUserEmail(User user) throws SparkPostException {
        String sendTo = user.getEmail();
        /*client.sendMessage(
                "notification-user@sparkpostbox.com",
                sendTo,
                "Welcome!" +  user.getName() + user.getLastname() + "to our App",
                "Algo",
                "<b>Auto sent via Spring Vaadin Calendar</b>");*/

    }

    public void sendReceiptEmail(Receipt receipt, UserService userService) throws SparkPostException {
        String sendToUser = receipt.getUser().getEmail();
        /*client.sendMessage(
                "notification-order@sparkpostbox.com",
                sendToUser,
                "Your Receipt: N.Order("+ receipt.getId() +") Date: " +  receipt.getDate(),
                //TODO ADD EMAIL CONTENT
                "<b>Order information:<b/>",
                "<b>Order information:</b>" +
                        "<br>Nombre: " + receipt.getUser().getName() +
                        "<br>Fecha: " + receipt.getDate() +
                        "<br>Cantidad de productos: " + receipt.getCantidadItems()
        );*/

        List<User> sellDepartamentUsers = userService.findByRole("Sells Departament");
        for(User user : sellDepartamentUsers){
            String sendTo = user.getEmail();
            /*client.sendMessage(
                    "inventory-departament@sparkpostbox.com",
                    sendTo,
                    "New Order Made: N.Order("+ receipt.getId() +") Date: " +  receipt.getDate(),
                    //TODO ADD EMAIL CONTENT
                    "<b>Order information:<b/>",
                    "<b>Auto sent via Spring Vaadin Calendar</b>");*/
        }
    }
}

