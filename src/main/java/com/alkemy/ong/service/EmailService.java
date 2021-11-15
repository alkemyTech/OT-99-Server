package com.alkemy.ong.service;

import java.io.IOException;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.model.Contact;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Content;

public interface EmailService {

  Response sendEmail(String to, String subject, Content content);

  Response sendWelcomeEmail(UserRegisterRequest user) throws IOException;

  Response sendContactRegisterEmail(ContactDto user) throws IOException;

}
