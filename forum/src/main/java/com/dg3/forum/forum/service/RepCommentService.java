package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.RepComment;

import java.util.List;

public interface RepCommentService {
    /*
     * Create rep comment
     * @param repcomment
     * @return object repcomment
     * */
    RepComment createRep_Comment(RepComment repComment);

    /*
     * Update repcomment
     * @param content_comment_rep and comment_rep_pk
     * */
    void updateRepcomment(String content_comment_rep, Long comment_rep_pk);

    /*
    * Delete repcomment
    * @param comment_rep_pk
    * */
    void deleteRepcomment(Long comment_rep_pk);

    /*
     * Check exist by repcomment
     * @param comment_rep_pk
     * @return object repcomment
     * */
    RepComment checkExistRepComment(Long comment_rep_pk);

    /*
    * List information repcomment
    * @param comment_rep_pk
    * @return list object repcomment
    * */
    List<RepComment> getAllRepComment(Long comment_rep_pk);

}
