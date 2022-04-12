package com.dg3.forum.forum.dto;

import com.dg3.forum.forum.entity.Users;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
