package email;

import org.apache.commons.mail.*;

public class email {

	public static void main(String[] args) {
		// Cria o e-mail
		HtmlEmail email = new HtmlEmail();
		try {
			email.setHostName("smtp.gmail.com");
			email.setStartTLSEnabled(true);
			email.setAuthentication("luciojbeirao@gmail.com", "tecnolog1435210122");
			email.addTo("luciojbeirao@gmail.com", "Lúcio José Beirão");
			email.setFrom("luciojbeirao@gmail.com", "Lucinho");
			email.setSubject("Teste de e-mail em formato HTML");
			 
			// configura a mensagem para o formato HTML
			email.setHtmlMsg("<html>Olá!!!<br /> Este e-mail é um email teste. Favor não responder.</html>");
			 
			// configure uma mensagem alternativa caso o servidor não suporte HTML
			email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
			 
			// envia o e-mail
			email.send();
			
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
	}

}
