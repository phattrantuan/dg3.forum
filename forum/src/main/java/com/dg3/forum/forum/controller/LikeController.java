package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Like;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.service.LikeService;
import com.dg3.forum.forum.service.UserService;
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

    @Autowired
    private UserService userService;

    @PostMapping("/create/posts/{thread_pk}")
    public ResponseEntity<Message> createLike_Posts(@PathVariable("thread_pk") Long thread_pk){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Users users = userService.findByEmail(userDetails.getUsername());

        Like user_like = likeService.checkExistLike_Posts(thread_pk, users.getUser_pk());

        if(user_like == null){
            Like like = new Like();
            like.setUser_pk(users.getUser_pk());
            like.setThread_pk(thread_pk);
            like.setComment_pk(null);
            like.setEnable_like(true);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("OK", "Like posts successfully",likeService.createLike_Posts(thread_pk, like))
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Message("Failed", "Like posts exist", "")
            );
        }
    }

    @DeleteMapping("/delete/posts/{thread_pk}")
    public ResponseEntity<Message> deleteLikePosts(@PathVariable("thread_pk") Long thread_pk){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Users users = userService.findByEmail(userDetails.getUsername());

        Like user_like = likeService.checkExistLike_Posts(thread_pk, users.getUser_pk());

        if(user_like != null){
            likeService.deleteLike_Posts(thread_pk, users.getUser_pk());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("OK", "Delete like posts successfully","")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Message("Failed", "Can't find like posts", "")
            );
        }
    }

    @GetMapping("/sumlike/{thread_pk}")
    public ResponseEntity<Message> sumLike(@PathVariable("thread_pk") Long thread_pk){
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Suscessfully", likeService.sumLike_Posts(thread_pk))
        );
    }
}
