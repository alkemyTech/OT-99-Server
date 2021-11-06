package com.alkemy.ong.email;

public enum MailMessage {
	
	REGISTER_TITTLE (" Registro de Cuenta: "),
	ACTIVITY_TITTLE (" Actividad Detectada: "),
	WELCOME_SUBJECT (" Bienvenido a Somos Mas! "),
	ACTIVITY_SUBJECT (" Alerta de Actividad. "),
	CONTACT_MAIL (" Mail: somosfundacionmas@gmail.com "),
	CONTACT_CELL (" Teléfono de contacto: 1160112988 ");
	
	private String value;
	
	MailMessage(String value) {
		
		this.value=value;
		
	}
	
	public String getValue() {
		
		return value;
	}
	
	public static String getWelcomeMsg(String firstname,String lastname) {
		
		return "Bienvenido/a " + firstname + " "+ lastname + " gracias por haberte registrado en nuestro sitio web.";
	}
}
