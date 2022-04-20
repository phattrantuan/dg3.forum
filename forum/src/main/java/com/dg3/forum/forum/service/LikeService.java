package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Like;

public interface LikeService {
    /*
    * Sum like by posts
    * */
    int sumLike_Posts(Long threak_pk);

    /*
    * Create like posts
    * */
    Like createLike_Posts(Long thread_pk, Like like);

    /*
    * Create like comment
    * */
    Like createLike_Comments(Long thread_pk, Like like);

    /*
    * Check exist by like posts
    * */
    Like checkExistLike_Posts(Long thread_pk, Long user_pk);

    /*
    *
    * */
    void deleteLike_Posts(Long thread_pk, Long user_pk);
}
