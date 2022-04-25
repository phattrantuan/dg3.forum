package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository

public interface UserstRepository extends JpaRepository<Users, Long> {
	/**
	 * Get user information by username
	 * @param username
	 * @return
	 */
	Users getByUsername(String username);

	/**
	 * Find user information by username
	 * @param username
	 * @return
	 */
	List<Users> findByUsername(String username);

	/**
	 * Check number phone
	 * @param phone_number
	 * @return
	 */
	@Query("SELECT u from Users u where u.phone_number = :phone_number")
	List<Users> existByPhone_number(String phone_number);

	/**
	 * Check email
	 * @param email
	 * @return
	 */
	@Query("SELECT u from Users u where u.email = :email")
	List<Users> existByEmail(String email);

	@Query("SELECT u from Users u where u.email = :email")
	Users findByEmail(String email);

	//insert users role dealer / manager
	Optional<Users> save(Optional<Users> users);
	


		    
}
