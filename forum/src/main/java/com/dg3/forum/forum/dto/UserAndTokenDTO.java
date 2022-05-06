package com.dg3.forum.forum.dto;


import com.dg3.forum.forum.entity.Users;

public class UserAndTokenDTO {
	Users user;
	String token;

	public UserAndTokenDTO() {

	}

	public UserAndTokenDTO(Users user, String token) {
		this.user = user;
		this.token = token;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
