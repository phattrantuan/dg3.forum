package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.service.DealerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerControllers {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DealerService dealerService;

    /*
     * Showing a list of dealer's posts
     * */
    @GetMapping("/all/{username}")
    public ResponseEntity<Message> showAllPostDealer(@PathVariable String username) {
        List<PostThread> listAllPost = dealerService.listAllPostDealer(username);
        return listAllPost.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "There is no list of posts by" + username, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "List all posts dealer", listAllPost)
                );
    }

    @PostMapping("/create/posts")
    public PostThread createPostsDealer(@RequestBody PostThread postThread){
        /*
        * Get the current date and time when the article was posted.
        * */
        Date dateNow = new Date();
        postThread.setTime_post_thread(dateNow);

        return dealerService.savePostsDealer(postThread);


    }

    @PutMapping("/update/posts/{thread_pk}")
    public void updatePostsDealer(@RequestBody PostThread postThread, @PathVariable("thread_pk") Long thread_pk){
        postThread.setThread_pk(thread_pk);
        dealerService.updatePostsDealer(postThread);
    }

    @DeleteMapping("/delete/posts/{thread_pk}")
    public void deletePostsDealer(@PathVariable("thread_pk") Long thread_pk){
        if(dealerService.checkExistByThread_pk(thread_pk) != null){
            dealerService.deletePostsDealer(thread_pk);

            if(dealerService.checkExistByThread_pk(thread_pk) != null){
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "Delete posts successfully", "")
                );
            }else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Failed", "Delete posts unsuccessfully", "")
                );
            }
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Message("Failed", "Cannot find posts to delete", "")
            );
        }
    }

    @GetMapping("/hello/{thread_pk}")
    public PostThread check(@PathVariable("thread_pk") Long thread_pk){
        return dealerService.checkExistByThread_pk(thread_pk);
    }
}
