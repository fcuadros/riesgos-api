package com.tp2.modulo.sgr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.appengine.api.utils.SystemProperty;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tp2.modulo.sgr.dao.AlertaDAO;
import com.tp2.modulo.sgr.model.Alerta;
import com.tp2.modulo.sgr.model.Control;
import com.tp2.modulo.sgr.model.Correo;
import com.tp2.modulo.sgr.model.RespuestaResponse;
import com.tp2.modulo.sgr.model.Riesgo;
import com.tp2.modulo.sgr.util.Utilitario;;

public class AlertaService {
	
		private static ResourceBundle rb = ResourceBundle.getBundle("template_riesgo");
	
		AlertaDAO AlertaDAO = new AlertaDAO();
		
		public void sendSimpleMessagesv2(String to, String subject, String text, List<Correo> co) throws UnirestException {			
			
			String MAILGUN_API_KEY = "key-e9e2ac4c9c7a752d72fa9a49ad5d84b1";
			String MAILGUN_DOMAIN_NAME = "sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org";
			//String body ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head></head><body><style></style><article class=\"bodymail\" style=\"background-color:#eee;padding:10px 20px\"><h2 class=\"mailtitle\" style=\"color:#333;font-size:20px;text-align:center\">Modulo Riesgos</h2><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">Notificación de riesgos, usted recibe este correo por estar asignado como responsable de ...</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">" + text + "</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Nombre del riesgo:</b> nombre del riesgo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Descripción:</b> descripción del riesgo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Persona que identificó:</b> Omar Diaz</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Fecha de registro:</b> 15/11/2018</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Tipo de escala:</b> Costo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Nivel del riesgo:</b> Medio</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Costo:</b> S/ 500.00</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Probabilidad:</b> 0.8</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">&nbsp;</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><img src=\"http://riesgos-backend.appspot.com/img/logo.png\" alt=\"\"></p></article></body></html>";
			
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				
				HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME + "/messages")
		        .basicAuth("api", MAILGUN_API_KEY)
                .queryString("from", "Administrador <riesgos-api@sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org>")
                .queryString("to", to)
                .queryString("subject", subject)
                //.queryString("text", "Testing out some Mailgun awesomeness!")
                .queryString("html", text)
                .asJson();		
		        request.getBody();

			} else {
				
//				try {				
//				 
//					JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
//					
//					emailSender.setUsername("riesgos-api@sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org");
//					emailSender.setPassword("123456789");
//					
//					Properties properties = new Properties();
//				    properties.setProperty("mail.transport.protocol", "smtp");
//				    properties.setProperty("mail.smtp.auth", "true");
//				    properties.setProperty("mail.smtp.starttls.enable", "false");
//	//			    properties.setProperty("mail.debug", Boolean.toString(this.debug));
//				    properties.setProperty("mail.smtp.host", "smtp.mailgun.org");
//				    properties.setProperty("mail.smtp.port", "25");
//	//			    properties.setProperty("mail.smtp.ssl.trust", this.trust);
//				    emailSender.setJavaMailProperties(properties);					
//					
//					MimeMessage message = emailSender.createMimeMessage(); 
//					MimeMessageHelper helper = new MimeMessageHelper(message,true);
//					
//					message.setContent(body, "text/html");
//	
//					try {
//						helper.setFrom("riesgos-api@sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org","Administrador de correos");
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//					
//					helper.setTo(to);					
//					
//					//COPIAS:
//					try {
//						if(!co.isEmpty()) {
//							for (Correo mail : co) {
//								helper.addCc(mail.getMail());
//							}
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//					
//					helper.setSubject(subject);
//					helper.setText(text, true);					
//	
//					emailSender.send(message);
//				} catch (MailException | MessagingException exception) {
//					exception.printStackTrace();
//				}				

			}
			

		}
		
		public ArrayList<Alerta> getAlertas() {
			ArrayList<Alerta> listAlertas = AlertaDAO.getAlertas();		
			return listAlertas;
		}
		
		public RespuestaResponse registrarAlerta(Alerta alerta) {
			
			RespuestaResponse respuestaResponse = new RespuestaResponse();
			boolean respuestaRegistrarRiesgo = false;
//			String respuestaRegistrarRiesgo="";
			
			respuestaRegistrarRiesgo = AlertaDAO.registrarAlerta(alerta);
			
			if (respuestaRegistrarRiesgo) {
				respuestaResponse.setCodigoRespuesta("0");
				respuestaResponse.setMensajeRespuesta("Exito");
			} else {
				respuestaResponse.setCodigoRespuesta("1");
				respuestaResponse.setMensajeRespuesta("Error");
			}
			
//			respuestaResponse.setCodigoRespuesta("0");
//			respuestaResponse.setMensajeRespuesta(respuestaRegistrarRiesgo);
			
			//Evaluar si corresponde notificar correo
//			Alerta alerta1 = AlertaDAO.getAlerta(63);
			if(alerta != null){
				//Si alerta está configura y activada, además riesgo alto
//				if("1".equals(alerta.getEstado()) 
//						&& 3 == riesgo.getNivelRiesgo() 
//						){
					//Enviar correo
					enviarMail(null, null, alerta);
//				}
			}
			
			
			return respuestaResponse;
		}
		
		
		public Alerta getAlertaByOpcionMenu(int idOpcionMenu) {
			Alerta alerta = AlertaDAO.getAlerta(idOpcionMenu);		
			return alerta;
		}
		
		
		public void enviarMail(Riesgo riesgo, Control control, Alerta alerta) {
			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {

						if(riesgo !=null && alerta != null){
							sendSimpleMessagesv2(alerta.getCorreo(),"NOTIFICACION DE CREACION DE RIESGO: ", Utilitario.formatMailRiesgo(rb.getString("nuevo_riesgo"),riesgo,alerta),null);
						}else if(control != null && alerta != null){
							sendSimpleMessagesv2(alerta.getCorreo(),"NOTIFICACION DE CREACION DE CONTROL: ", Utilitario.formatMailControl(rb.getString("nuevo_control"),control,alerta),null);
						}else{
							sendSimpleMessagesv2(alerta.getCorreo(),"NOTIFICACION DE CREACION DE RIESGO: ", rb.getString("nuevo_riesgo"),null);
						}

						System.out.println("SendMail: Correo Enviado");
					} catch (Exception e) {

						System.err.println("SendMail"+ e.getMessage());
						e.printStackTrace();
					}finally {
						System.err.println("SendMail: TerminÃ³ Proceso");
					}
				}
			});
			thread.start();
		}
		
	}
