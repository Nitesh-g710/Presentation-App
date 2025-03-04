package com.in.main.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.in.main.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendRatingEmailWithAttachment(String toEmail, String subject, String message, byte[] pdfData) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(message, true); // Enable HTML format

			// Attach PDF file
			helper.addAttachment("Rating_Report.pdf", () -> new java.io.ByteArrayInputStream(pdfData));

			mailSender.send(mimeMessage);
			System.out.println("Email with PDF sent successfully to " + toEmail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
