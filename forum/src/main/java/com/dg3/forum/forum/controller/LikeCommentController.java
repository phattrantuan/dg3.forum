package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.dto.LikeCommentPostsDTO;
import com.dg3.forum.forum.service.LikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sum")
public class LikeCommentController {
    @Autowired
    private LikeCommentService like_commentService;

    /**
     * sum like and sum comment
     * list username by posts
     * @param thread_pk
     * @return object Like_Comment_Posts
     */
    @GetMapping("/like_comment/{thread_pk}")
    public LikeCommentPostsDTO sumLike_CommentPosts(@PathVariable("thread_pk") Long thread_pk){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new Message("OK", "Sum like and comment successfully", like_commentService.sumCommentAndLike(thread_pk))
//        );
        return like_commentService.sumCommentAndLike(thread_pk);
    }
}
