package com.heliverse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Message {
	
	private String role;
	private String content;
	public Message(String role, String content) {
		super();
		this.role = role;
		this.content = content;
	}
	public Message() {
		super();
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
