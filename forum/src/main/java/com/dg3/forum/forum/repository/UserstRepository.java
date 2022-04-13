package com.dg3.forum.forum.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.dg3.forum.forum.entity.Users;

@Repository

public interface UserstRepository extends JpaRepository<Users, Long> {
	@Query("SELECT u from Users u where u.user_pk = :user_pk")
	Users findRoomByUserId(@PathVariable("user_pk") Long user_pk);

	//findByUsername*
	List<Users> findByUsername(String username);
	//existByPhone_number*
	@Query("SELECT u from Users u where u.phone_number = :phone_number")
	List<Users> existByPhone_number(String phone_number);

} 
