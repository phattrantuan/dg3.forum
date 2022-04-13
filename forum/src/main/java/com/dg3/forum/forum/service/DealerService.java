package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.PostThread;
import java.util.List;

public interface DealerService {
    /*
    * List of dealer posts
    * Request is String username
    * Respone is List of dealer posts
    * */
    List<PostThread> listAllPostDealer (String username);

    /*
    * Save information posts of dealer
    * Request is Object PostThread postThread
    * Respone is information is saved
    * */
    PostThread savePostsDealer(PostThread postThread);

    /*
    * Update information posts of dealer
    * Request is Object PostThread postThread
    * Respone is information is update
    * */
    void updatePostsDealer(PostThread postThread);

    /*
    * Delete information posts of dealer
    * Request is Long threak_pk (primary key of table PostDealer)
    * Respone is information is delete from database
    * */
    void deletePostsDealer(Long thread_pk);

    /*
    *   Check the information of the post exists or not through thread_pk
    * */
    PostThread checkExistByThread_pk(Long thread_pk);
}
