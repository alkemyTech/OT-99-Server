package com.alkemy.ong.service.impl;

import java.io.IOException;
import com.alkemy.ong.config.SendGridConfig;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.email.MailMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final String endpoint = "mail/send";
    private static final String TEXT_HTML="text/html";

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    @Autowired
    SendGridConfig sendGridConfig;
    
	@Autowired
	private TemplateEngine templateEngine;

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

	@Override
	public Response sendWelcomeEmail(UserRegisterRequest user) throws IOException {
		
		String mailBody=preparedWelcomeBodyEmail(user);
		
		Content content= new Content(TEXT_HTML,mailBody);
		
		Response response=sendEmail(user.getEmail(),MailMessage.WELCOME_SUBJECT.getValue(),content);
		
		return response;
		
	}

    @Override
    public Response sendContactRegisterEmail(ContactDto user) throws IOException {
        Content content = new Content("text/plain",MailMessage.getRegisterContactMsg(user.getName()));
        Response response = sendEmail(user.getEmail(),"¡Gracias por tu registro!",content);
        return response;
    }


    private String preparedWelcomeBodyEmail(UserRegisterRequest user) {
		
		Context context=new Context();
		context.setVariable("tittleContent", MailMessage.REGISTER_TITTLE.getValue());
		context.setVariable("textContent", MailMessage.getWelcomeMsg(user.getFirstName(), user.getLastName() ));
		context.setVariable("mailContact", MailMessage.CONTACT_MAIL.getValue());
		context.setVariable("cellContact", MailMessage.CONTACT_CELL.getValue());
		
		return templateEngine.process("MailTemplate.html", context);
		
	}

}
