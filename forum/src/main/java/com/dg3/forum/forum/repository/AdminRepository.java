package com.dg3.forum.forum.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dg3.forum.forum.entity.Medicine;
import com.dg3.forum.forum.util.DateCurrent;

@Repository
public interface AdminRepository extends JpaRepository<Medicine, Long> {
//	@Modifying
//	@Query(
//	  value = 
//	    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//	  nativeQuery = true)
//	void insertUser(@Param("name") String name, @Param("age") Integer age, 
//	  @Param("status") Integer status, @Param("email") String email);
	/**
	 * insert information user through dto Request is String title_thread, String
	 * content_of_thread, Long post_topic_pk, boolean enable_post_thread, Long
	 * thread_pk Respone is Posts update
	 */
	@Modifying
	@Transactional
	@Query(value = "insert into Users (email, password, username, role, phone_number, address, date_of_birth, ban_account, img_avatar, description, created_date, expire, enable_users)"
			+ " values (:email, :password, :username, :role, :phone_number, :address, :date_of_birth, 'false', :img_avatar, :description, CURRENT_DATE, :expire, 'true')", nativeQuery = true)
	int insertUserManagerOrDealer(@Param("email") String email, @Param("password") String password,
			@Param("username") String username, @Param("role") String role, @Param("phone_number") String phone_number,
			@Param("address") String address, @Param("date_of_birth") Date date_of_birth,
			@Param("img_avatar") String img_avatar, @Param("description") String description,
			@Param("expire") Date expire);

}
