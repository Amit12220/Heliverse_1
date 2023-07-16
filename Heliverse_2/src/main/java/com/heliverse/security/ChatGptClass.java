package com.heliverse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Component

public class ChatGptClass {
	
//	@Value("${chatgpt.key}")
//	private String token;
	@Autowired
	private JwtUtils jwu;
	
	
	@Bean
	public RestTemplate getrestTemplate()
	{
		RestTemplate resttemp=new RestTemplate();
		resttemp.getInterceptors().add((request,body,execution)->{
			request.getHeaders().add("Authorization","Bearer"+ jwu.getJwtToken());
			return execution.execute(request, body);
		});
		return resttemp;
	}
	
	

}
