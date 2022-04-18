package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserstRepository extends JpaRepository<Users, Long> {
	//@Query("SELECT u from Users u where u.user_pk = :user_pk")
	//Users findRoomByUserId(@PathVariable("user_pk") Long user_pk);


//	@Query("DELETE  from Users u where u.user_pk = :user_pk")
//	Optional<Users> deleteAccount(@PathVariable("user_pk") Long user_pk);

	/*
	* Get user information by username
	* */
	Users getByUsername(String username);

	/*
	 * Find user information by username
	 * */
	List<Users> findByUsername(String username);
  
	//existByPhone_number
	@Query("SELECT u from Users u where u.phone_number = :phone_number")
	List<Users> existByPhone_number(String phone_number);

	//existByGmail*
	@Query("SELECT u from Users u where u.email = :email")
	List<Users> existByEmail(String email);
	
	
	
	//insert users role dealer / manager
	Optional<Users> save(Optional<Users> users);



} 
