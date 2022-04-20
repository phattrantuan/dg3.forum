package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Like;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/create/{thread_pk}")
    public ResponseEntity<Message> createLike_Posts(@PathVariable("thread_pk") Long threak_pk){
//        Like user_like = likeService.checkExistLike_Posts(threak_pk, like.getUser_pk());
//
//        if(user_like == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("OK", "Like posts successfully","")
            );
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new Message("Failed", "Like posts exist", "")
//            );
//        }
    }

    @GetMapping("/sumlike/{thread_pk}")
    public ResponseEntity<Message> sumLike(@PathVariable("thread_pk") Long thread_pk){
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Suscessfully", likeService.sumLike_Posts(thread_pk))
        );
    }
}
