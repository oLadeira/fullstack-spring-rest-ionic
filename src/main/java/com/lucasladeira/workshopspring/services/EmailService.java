package com.lucasladeira.workshopspring.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucasladeira.workshopspring.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
}
