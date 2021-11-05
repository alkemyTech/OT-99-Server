package com.alkemy.ong.service.impl;

import java.io.IOException;

import com.alkemy.ong.config.SendGridConfig;
import com.alkemy.ong.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final String endpoint = "mail/send";

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    @Autowired
    SendGridConfig sendGridConfig;

    @Override
    public Response sendEmail(String email, String subject, Content content) {
        Response response = new Response();
        Email from = new Email(this.senderEmail);
        Email to = new Email(email);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sendGrid = new SendGrid(sendGridConfig.getKey());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(this.endpoint);
            request.setBody(mail.build());
            response = sendGrid.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;

    }

}
