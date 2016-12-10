package com.challenge.Components;

import com.challenge.Model.Receipt;
import com.challenge.Model.User;
import com.challenge.Services.ReceiptService;
import com.challenge.Services.UserService;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by marti on 10/12/2016.
 */

@Component
public class Email {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private UserService userService;


    public void sendUserEmail(User user) throws SparkPostException {
        String API_KEY = "e1ec696d48a43f251c55fc3c63780f8ded934f69";
        Client client = new Client(API_KEY);
        String sendTo = user.getEmail();

        client.sendMessage(
                "Challenge@sparkpostbox.com",
                sendTo,
                "Welcome!" +  user.getName() + user.getLastname() + "to our App",
                "<b>Your information:</b>\n"
                        + "<p>Name: " + user.getName() + " " + user.getLastname()+"</p>"
                        + "<p>Email: " + user.getEmail() + "</p>"
                        + "<p>Password: " + user.getPassword() + "</p>",
                "<b>Auto sent via Spring Vaadin Calendar</b>");

    }

    public void sendReceiptEmail(Receipt receipt) throws SparkPostException {
        String API_KEY = "e1ec696d48a43f251c55fc3c63780f8ded934f69";
        Client client = new Client(API_KEY);
        String sendTo = receipt.getUser().getEmail();

        client.sendMessage(
                "Challenge@sparkpostbox.com",
                sendTo,
                "Your Receipt: N.Order("+ receipt.getId() +") Date: " +  receipt.getDate(),
                //TODO ADD EMAIL CONTENT
                "Your information:\n",
                "<b>Auto sent via Spring Vaadin Calendar</b>");

    }

}

