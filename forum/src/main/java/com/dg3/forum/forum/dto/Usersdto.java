package com.dg3.forum.forum.dto;

import com.dg3.forum.forum.entity.Users;

<<<<<<< HEAD

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
>>>>>>> phat
@NoArgsConstructor
public class Usersdto {
	public String password;
	public String username;
	
public Usersdto(Users users)
{
	this.password = users.getPassword();
	this.username = users.getUsername();
}
	
}
