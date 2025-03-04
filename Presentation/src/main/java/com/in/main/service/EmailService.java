package com.in.main.service;

public interface EmailService {

	
	void sendRatingEmailWithAttachment(String toEmail, String subject, String message, byte[] pdfData) ;
}
