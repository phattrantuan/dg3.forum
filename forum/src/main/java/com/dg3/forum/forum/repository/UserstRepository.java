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
	
	
	
	//insert users role dealer / manager
	Optional<Users> save(Optional<Users> users);

		/**
	    * insert information user through dto
	    * Request is String title_thread, String content_of_thread, Long post_topic_pk, boolean enable_post_thread, Long thread_pk
	    * Respone is Posts update
	    * */
	    @Modifying
	    @Transactional
	    @Query(value = "update post_thread set " +
	                    "title_thread = :title_thread," +
	                    "content_of_thread = :content_of_thread," +
	                    "post_topic_pk = :post_topic_pk," +
	                    "enable_post_thread = :enable_post_thread " +
	                    "where thread_pk = :thread_pk", nativeQuery = true)
	    void updateByPosts(@Param("title_thread") String title_thread,
	                             @Param("content_of_thread") String content_of_thread,
	                             @Param("post_topic_pk") Long post_topic_pk,
	                             @Param("enable_post_thread") boolean enable_post_thread,
	                             @Param("thread_pk") Long thread_pk);


} 
