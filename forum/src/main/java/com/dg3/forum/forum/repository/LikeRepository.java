package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    /*
    * Sum like by posts
    * */
    @Modifying
    @Transactional
    @Query(value = "select count(*) as sum from likes " +
                    "inner join post_thread on likes.thread_pk = post_thread.thread_pk " +
                    "inner join users on users.user_pk = likes.user_pk " +
                    "inner join comment on comment.comment_pk = likes.comment_pk " +
                    "where likes.thread_pk = :thread_pk", nativeQuery = true)
    int sumLike(@Param("thread_pk") Long thread_pk);

    /*
    * Find information like posts
    * Request is threak_pk and user_pk
    * */
    @Transactional
    @Query(value = "select likes.* as sum from likes " +
            "inner join post_thread on likes.thread_pk = post_thread.thread_pk " +
            "inner join users on users.user_pk = likes.user_pk " +
            "inner join comment on comment.comment_pk = likes.comment_pk " +
            "where likes.thread_pk = :thread_pk and users.user_pk = :user_pk", nativeQuery = true)
    Like findByThreak_pkAndUser_pk(@Param("thread_pk") Long thread_pk, @Param("user_pk") Long user_pk);
}
