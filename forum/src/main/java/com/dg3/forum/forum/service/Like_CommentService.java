package com.dg3.forum.forum.service;

import com.dg3.forum.forum.dto.Like_Comment_Posts;

public interface Like_CommentService {
    /*
     * Sum comment and like by posts
     * @param thread_pk
     * @return Like_Comment_Posts
     * */
    Like_Comment_Posts sumCommentAndLike(Long thread_pk);
}
