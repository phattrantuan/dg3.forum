package com.dg3.forum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dg3.forum.forum.entity.Medicine;

@Repository
public interface AdminRepository  extends JpaRepository<Medicine, Long> {

//@Modifying
//@Query(
//  value =
//    "insert into users (email, password, username, status) values (:name, :age, :email, :status)",
//  nativeQuery = true)
//void insertUser(@Param("name") String name, @Param("age") Integer age,
//  @Param("status") Integer status, @Param("email") String email);
	}
