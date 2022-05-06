package com.dg3.forum.forum.service;

import com.dg3.forum.forum.dto.LikeCommentPostsDTO;

public interface LikeCommentService {
    /*
     * Sum comment and like by posts
     * @param thread_pk
     * @return Like_Comment_Posts
     * */
    LikeCommentPostsDTO sumCommentAndLike(Long thread_pk);
}
