package com.dg3.forum.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.service.LikeService;
import com.dg3.forum.forum.service.PostThreadService;

@RestController
@RequestMapping(path="/api/v1/post_thread")
public class PostThreadController {

	@Autowired
	private PostThreadService service;
	
	@Autowired
	private  LikeService likeService;

	@PostMapping("/insert")
	ResponseEntity<Message> insert(@RequestBody PostThread post) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Message("Thanh cong!", "Tao bai viet thanh cong", service.savePosts(post)));

	}
	
	@GetMapping()
	ResponseEntity<Message> getAll() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Message("Thành công!", null, service.getAllPostThreads()
						.stream()
						.map(thread->{
							
//						thread.setLikeCount(likeService.sumLike_Posts(thread.getThread_pk()));
							
//							System.out.print(likeService.sumLike_Posts((long) 1));
							
							return thread;
						})));

	}

}
