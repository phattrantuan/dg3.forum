package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Comment;

import java.util.List;

public interface CommentService {
    /*
    * Create comment posts
    * */
    Comment createCommentPosts(Comment comment);

    /*
    * Show comment by posts
    * */
    List<Comment> showAllComment_Posts(Long thread_pk);

    /*
    * Update comment by posts
    * */
    void updateCommnet_Posts(Long comment_pk, String content_comment);

    /*
    * Get information comment by posts
    * */
    Comment getByComment(Long comment);

    /*
    * Delete comment by posts
    * */
    void deleteComment(Long comment_pk);

}
