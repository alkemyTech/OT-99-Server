package com.alkemy.ong.email;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplateMessage {

private  final String REGISTER_TITTLE="Registro de Cuenta:";
	
	private  final String ACTIVITY_TITTLE="Actividad Detectada:";
	
	private  final String WELCOME_SUBJECT="Bienvenido a Somos Mas!";
	
	private  final String ACTIVITY_SUBJECT="Alerta de Actividad.";
	
	private  final String CONTACT_MAIL="Mail: somosfundacionmas@gmail.com";
			
	private  final String CONTACT_CELL="Teléfono de contacto: 1160112988";
	
	public String getRegisterTittle() {
		return REGISTER_TITTLE;
	}

	public String getActivityTittle() {
		return ACTIVITY_TITTLE;
	}

	public String getWelcomeSubject() {
		return WELCOME_SUBJECT;
	}

	public String getActivitySubject() {
		return ACTIVITY_SUBJECT;
	}

	public String getContactMail() {
		return CONTACT_MAIL;
	}
	
	public String getContactCell() {
		return CONTACT_CELL;
	}
	
	public String getWelcomeBody(String username) {
		
		return "Bienvenido/a " + username + " gracias por haberte registrado en nuestro sitio web.";
	}
}
