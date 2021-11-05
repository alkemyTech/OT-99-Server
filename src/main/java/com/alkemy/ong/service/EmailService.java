package com.alkemy.ong.service;

import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Content;

public interface EmailService {

  Response sendEmail(String to, String subject, Content content);

}
