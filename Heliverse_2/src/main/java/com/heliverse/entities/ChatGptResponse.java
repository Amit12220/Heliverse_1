package com.heliverse.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ChatGptResponse {
	
	private List<Choice> choices;
	
	
	
	public static class Choice
	{
		private int index;
		private Message message;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public Message getMessage() {
			return message;
		}
		public void setMessage(Message message) {
			this.message = message;
		}
		
		
		
		public Choice() {
			super();
		}
		public Choice(int index, Message message) {
			super();
			this.index = index;
			this.message = message;
		}
		
	}



	public List<Choice> getChoices() {
		return choices;
	}



	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public ChatGptResponse() {
		super();
	
	}


	public ChatGptResponse(List<Choice> choices) {
		super();
		this.choices = choices;
	}
	
	
	

}
