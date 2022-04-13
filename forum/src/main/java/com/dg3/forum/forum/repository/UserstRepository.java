package com.dg3.forum.forum.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;

@Repository


public interface UserstRepository extends JpaRepository<Users, Long> {
	@Query("SELECT u from Users u where u.user_pk = :user_pk")
	Users findRoomByUserId(@PathVariable("user_pk") Long user_pk);

	void save(PostThread postThread);

	void save(PostTopic postTopic);

//	@Query("DELETE  from Users u where u.user_pk = :user_pk")
//	Optional<Users> deleteAccount(@PathVariable("user_pk") Long user_pk);
} 
