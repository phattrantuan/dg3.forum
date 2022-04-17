package com.dg3.forum.forum.service;

import java.util.List;
import java.util.Optional;

import org.thymeleaf.util.StringUtils;

import com.dg3.forum.forum.entity.Users;



public interface UserService {
	//get all information account users
	List<Users> listAll();
	//get only account user
	Users getUsers(Long id);
	//check exist account account user
	boolean existById(Long id);
	//delete account account user
	void deleteAccount(Long id);
	//get only account user
	Optional<Users> findById(Long user_pk);

	//find by username*
//	List<Users> findByUsername(String username);
	//save*
	Users save(Users users);
	// Check phone number*
	List<Users> checkPhone_number(String phone_number);
	//check email
	List<Users> checkEmail(String email);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
