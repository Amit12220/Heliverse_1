package com.heliverse.entities;

import java.util.ArrayList;
import java.util.List;

public class ChatGptRequest {
	
	private String model;
	
	private List<Message>  messages;
	
	
	

	public ChatGptRequest() {
		super();
	}

	public ChatGptRequest(String model, String prompt) {
		
		this.model = model;
		this.messages=new ArrayList<Message>();
		this.messages.add(new Message("user",prompt));
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Message> getMessage() {
		return messages;
	}

	public void setMessage(List<Message> messages) {
		this.messages = messages;
	}
	
	
	
	
	
	

}
