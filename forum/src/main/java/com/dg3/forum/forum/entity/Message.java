package com.dg3.forum.forum.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	 private String statusCode;
	    private String message;
	    private Object object;
		public Message(String statusCode, String message, Object object) {
			super();
			this.statusCode = statusCode;
			this.message = message;
			this.object = object;
		}
		public Message() {
			
		}
}
