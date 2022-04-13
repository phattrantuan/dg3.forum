package com.dg3.forum.forum.service;

import java.util.List;
import java.util.Optional;

import com.dg3.forum.forum.entity.Users;

public interface UserService {
	//get all information account users
	public List<Users> listAll();
	//get only account user
	public Users getUsers(Long id);
	//check exist account account user
	public boolean existById(Long id);
	//delete account account user
	public void deleteAccount(Long id);
	//get only account user
	public Optional<Users> findById(Long user_pk);
}
