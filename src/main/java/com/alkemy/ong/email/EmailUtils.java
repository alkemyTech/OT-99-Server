package com.alkemy.ong.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Component
public class EmailUtils {

	private static final String TEXT_HTML="text/html";
	
	@Value("${API_SENDGRID_KEY}")
	private String API_KEY;
	
	@Value("${MAIL_SENDER}")
	private String EMAIL_FROM;
	
	@Autowired
	EmailTemplate emailTemplate;

	@Autowired
	private EmailTemplateMessage templateMessage;
	
	public Mail preparedWelcomeEmail(String username) {
		
		Email mailSender = new Email(EMAIL_FROM);

		Email mailReceiver = new Email(username);

		Content content = new Content(TEXT_HTML,
				emailTemplate.getPreparedBody(
						templateMessage.getRegisterTittle(),
						templateMessage.getWelcomeBody(username),
						templateMessage.getContactMail(),
						templateMessage.getContactCell())
				);

		return  new Mail(mailSender, templateMessage.getWelcomeSubject(), mailReceiver, content);
		
	}
	
}
