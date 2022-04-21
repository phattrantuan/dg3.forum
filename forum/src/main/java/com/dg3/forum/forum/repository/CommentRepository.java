package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.Comment;
import com.dg3.forum.forum.entity.PostThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /*
    * Update content comment by posts
    * */
    @Modifying
    @Transactional
    @Query(value = "update comment set content_comment = :content_comment " +
                    "where comment_pk = :comment_pk", nativeQuery = true)
    void updateContent_Comment(@Param("content_comment") String content_comment, @Param("comment_pk") Long comment_pk);

    /*
    * Show comment by posts
    * */
    @Transactional
    @Query(value = "select * from comment where thread_pk = :thread_pk", nativeQuery = true)
    List<Comment> showComment_Posts(@Param("thread_pk") Long thread_pk);

    /*
    * Get information comment by posts
    * */
    @Transactional
    @Query(value = "select * from comment where comment_pk = :comment_pk", nativeQuery = true)
    Comment getById_Comment(@Param("comment_pk") Long comment_pk);

    /*
    * Delete comment by posts
    * */
    @Modifying
    @Transactional
    @Query(value = "delete from comment where comment_pk = :comment_pk", nativeQuery = true)
    void deleteId_Comment(@Param("comment_pk") Long comment_pk);

}
