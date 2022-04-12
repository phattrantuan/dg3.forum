package com.dg3.forum.forum.entity;

<<<<<<< HEAD

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> phat
public class Message {
	 private String statusCode;
	    private String message;
	    private Object object;
		
}
