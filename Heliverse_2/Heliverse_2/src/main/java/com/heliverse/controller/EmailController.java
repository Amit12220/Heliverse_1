package com.heliverse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heliverse.entities.EmailDetails;
import com.heliverse.payload.JwtResponse;
import com.heliverse.service.EmailService;

 
// Annotation
@RestController
// Class
public class EmailController {
 
    @Autowired 
    private EmailService emailService;
 
    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody EmailDetails details)
    {
    	 return ResponseEntity.ok(emailService.sendSimpleMail(details));
    	
        
    }
 
    
}
