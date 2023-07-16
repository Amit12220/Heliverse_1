package com.heliverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.heliverse.entities.ChatGptRequest;
import com.heliverse.entities.ChatGptResponse;


@RestController
@RequestMapping("/chat")
public class ChatGptController {
	
	@Value("/${chatgpt.model}")
	private String model;
	
	@Value("${chatgpt.url}")
	private String url;
	
	@Autowired
	private RestTemplate rest;
	
	 @GetMapping("/mess/{prompt}")
	 public String fetchData(@PathVariable("prompt") String prompt)
	  {
		  ChatGptRequest request=new ChatGptRequest(model,prompt);
		  ChatGptResponse response=rest.postForObject(url, request,ChatGptResponse.class );
		  
		  return response.getChoices().get(0).getMessage().getContent();
	  }

}
