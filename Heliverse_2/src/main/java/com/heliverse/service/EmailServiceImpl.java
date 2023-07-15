package com.heliverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.heliverse.entities.EmailDetails;

import lombok.Value;
@Service
public class EmailServiceImpl implements EmailService{

	  @Autowired
	  private JavaMailSender javaMailSender;
	      
	    private String sender="ajha.9614938@gmail.com";
	   
	    public String sendSimpleMail(EmailDetails details)
	    {
	 
	       
	        try {
	 
	         
	            SimpleMailMessage mailMessage
	                = new SimpleMailMessage();
	 
	         
	            mailMessage.setFrom(sender);
	            mailMessage.setTo(details.getRecipient());
	            mailMessage.setText(details.getMsgBody());
	            mailMessage.setSubject(details.getSubject());
	 
	            
	            javaMailSender.send(mailMessage);
	            return "Mail Sent Successfully...";
	        }
	 
	      
	        catch (Exception e) {
	            return "Error while Sending Mail";
	        }
	    }

}
