package com.tp2.modulo.sgr.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tp2.modulo.sgr.dao.AlertaDAO;
import com.tp2.modulo.sgr.model.Correo;;

public class AlertaService {
	
		AlertaDAO AlertaDAO = new AlertaDAO();
		
//		@Autowired
		public JavaMailSender emailSender;
		
		public void sendSimpleMessage(String to, String subject, String text) {
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(to);
				message.setSubject(subject);
				message.setText(text);

				emailSender.send(message);
			} catch (MailException exception) {
				exception.printStackTrace();
			}
		}

		public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
				String... templateArgs) {
			String text = String.format(template.getText(), templateArgs);
			sendSimpleMessage(to, subject, text);
		}

		public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
			try {
				MimeMessage message = emailSender.createMimeMessage();
				// pass 'true' to the constructor to create a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text);

				FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
				helper.addAttachment("Invoice", file);

				emailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		public void sendSimpleMessagesv2(String to, String subject, String text, List<Correo> co) {
			try {
				 
				JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
				
				emailSender.setUsername("testmail@nextsmart.net");
				emailSender.setPassword("X.W.~GICK@*)RPIAV]");
				
				Properties properties = new Properties();
			    properties.setProperty("mail.transport.protocol", "smtp");
			    properties.setProperty("mail.smtp.auth", "true");
			    properties.setProperty("mail.smtp.starttls.enable", "false");
//			    properties.setProperty("mail.debug", Boolean.toString(this.debug));
			    properties.setProperty("mail.smtp.host", "nextsmartservers.com");
			    properties.setProperty("mail.smtp.port", "25");
//			    properties.setProperty("mail.smtp.ssl.trust", this.trust);
			    emailSender.setJavaMailProperties(properties);
				
				
				MimeMessage message = emailSender.createMimeMessage(); 
//				MimeMessageHelper helper = new MimeMessageHelper(message);
				MimeMessageHelper helper = new MimeMessageHelper(message,true);

//				SimpleMailMessage message = new SimpleMailMessage();
				try {
//					helper.setFrom(getProp("spring.mail.from_global"),getProp("spring.mail.from_global_name"));
					helper.setFrom("djuarez7@hotmail.com","Administrador de correos");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				helper.setTo(to);
				
				
				//COPIAS:
				try {
					if(!co.isEmpty()) {
						for (Correo mail : co) {
							helper.addCc(mail.getMail());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				helper.setSubject(subject);
				helper.setText(text, true);
				

				emailSender.send(message);
			} catch (MailException | MessagingException exception) {
				exception.printStackTrace();
			}
		}

	}
