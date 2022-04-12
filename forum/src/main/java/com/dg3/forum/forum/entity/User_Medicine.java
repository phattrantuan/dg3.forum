package com.dg3.forum.forum.entity;

import lombok.Data;

@Data
public class User_Medicine {
	private Long user_pk;
	private Long medicine_pk;
	public User_Medicine(Long user_pk, Long medicine_pk) {
		super();
		this.user_pk = user_pk;
		this.medicine_pk = medicine_pk;
	}
	public User_Medicine() {
		
	}
}
