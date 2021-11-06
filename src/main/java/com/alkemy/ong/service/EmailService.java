package com.alkemy.ong.service;

import java.io.IOException;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Content;

public interface EmailService {

  Response sendEmail(String to, String subject, Content content);

  Response sendWelcomeEmail(UserRegisterResponse user) throws IOException;

}
