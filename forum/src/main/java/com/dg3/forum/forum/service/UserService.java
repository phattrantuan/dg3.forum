package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Users;

import java.util.List;
import java.util.Optional;


public interface UserService {
	/**
	 * get show all users
	 * @return
	 */
	List<Users> listAll();

	//get only account user
	Users getUsers(Long id);

	//check exist account account user
	boolean existById(Long id);

	/**
	 * delete user
	 * @param id
	 */
	void deleteAccount(Long id);

	/**
	 * Find by id
	 * @param user_pk
	 * @return
	 */
	Optional<Users> findById(Long user_pk);

	/**
	 * Find by name user
	 * @param username
	 * @return
	 */
	List<Users> findByUsername(String username);

	/**
	 * Save
	 * @param users
	 * @return
	 */
	Users save(Users users);
//
	/**
	 * Check number phone
	 * @param phone_number
	 * @return
	 */
	List<Users> checkPhone_number(String phone_number);

	/**
	 * check email
	 * @param email
	 * @return
	 */
	List<Users> checkEmail(String email);

	/*
	 * Find email
	 * */
	Users findByEmail(String email);
	
	void updateInformationUser(Users users);
	


}
