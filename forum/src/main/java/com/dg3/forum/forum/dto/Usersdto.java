package com.dg3.forum.forum.dto;

import com.dg3.forum.forum.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class Usersdto {
	public String password;
	public String username;
	
public Usersdto(Users users)
{
	this.password = users.password;
	this.username = users.username;
}
	
}
