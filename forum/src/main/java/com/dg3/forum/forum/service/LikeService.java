package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Like;

public interface LikeService {
    /*
    * Create like posts
    * @param thread_pk and like
    * @return object like
    * */
    Like createLike_Posts(Long thread_pk, Like like);

    /*
    * Create like comment
    * @param thread_pk and like
    * @return object like
    * */
    Like createLike_Comments(Long thread_pk, Like like);

    /*
    * Check exist by like posts
    * @param thread_pk and user_pk
    * @return object like
    * */
    Like checkExistLike_Posts(Long thread_pk, Long user_pk);

    /*
    * Check exist by like comment
    * @param thread_pk, user_pk and comment_pk
    * @return object like
    * */
    Like checkExistLike_Comment(Long thread_pk, Long user_pk, Long comment_pk);

    /*
    * Delete like posts
    * @param thread_pk and user_pk
    * */
    void deleteLike_Posts(Long thread_pk, Long user_pk);

    /*
     * Delete like comment posts
     * @param thread_pk, user_pk and comment_pk
     * */
    void deleteLike_Comment(Long thread_pk, Long user_pk, Long comment_pk);
}
