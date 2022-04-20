package com.dg3.forum.forum.dto;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dg3.forum.forum.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "roles", "authorities" })
@Getter
@Setter
@NoArgsConstructor
public class Usersdto {
	public String password;
	public String username;
	public String role;
	
	public Usersdto(Users entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
//	public List<GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//			authorities.add(new SimpleGrantedAuthority(role));
//
//		return authorities;
//	}
	
}
