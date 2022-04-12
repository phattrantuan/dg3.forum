package com.dg3.forum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.dg3.forum.forum.entity.Users;
@Repository
public interface UserstRepository extends JpaRepository<Users, Long>{
//	 @Query("SELECT u from Users u where u.user_pk = :user_pk")
//	    Users findRoomByUserId(@PathVariable("user_pk") Long user_pk);
}
