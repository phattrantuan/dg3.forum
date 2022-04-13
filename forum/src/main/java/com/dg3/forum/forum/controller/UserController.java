package com.dg3.forum.forum.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.serviceimpl.UserServiceimpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceimpl service;
    @Autowired 
    UserstRepository repository;
 

//	@GetMapping
//	public List<Users> listAll() {
//		return service.listAll();
//	}

    @GetMapping
    public ResponseEntity<Message> listAll() {
        LOGGER.error("listAll");
        List<Users> users = service.listAll();
        return users.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "not found user", "")
                )
                : ResponseEntity.status(HttpStatus.OK).body(
                new Message("Ok", "have users", users)
        )
                ;
    }

//
//	@GetMapping("/{id}")
//	public Optional<Users> getid(@PathVariable Long id) {
//		LOGGER.error("logid");
//		return userRepository.findById(id);
//				
//	
//	}
//	@PostMapping
//	public Users Users(@RequestBody Users users) {
//		return userRepository.save(users);
//	}
//	@PutMapping
//	public Users UpdateUser(@RequestBody Users users) {
//
//		return userRepository.save(users);
//	}
//	@DeleteMapping("{id}")
//	public void a(@PathVariable Long id) {
//
//		 userRepository.deleteById(id);
//	}
//	
    @GetMapping("/{user_pk}")
    public ResponseEntity<Message> findById(@PathVariable Long user_pk) {
        LOGGER.error("findById");
        Optional<Users> users = service.findById(user_pk);
        return users.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Ok", "have users", users)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "not found user" + user_pk, "")
                );
    }
    
    @DeleteMapping("/{user_pk}")
    ResponseEntity<Message> deleteProduct(@PathVariable Long user_pk) {
       
        if( service.existById(user_pk)) {
           service.deleteAccount(user_pk);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Message("failed", "Cannot find product to delete", "")
        );
    }
    
 @GetMapping("/insert")
 public void ionetoone() {
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	 
	 PostTopic postTopic = new PostTopic();
	 postTopic.setEnable_post_topic(true);
	 postTopic.setName_topic("sthing2");
	 
	 
	 
	 PostThread postThread = new PostThread();
	 postThread.setUser_pk(1L);
	 postThread.setContent_of_thread("hoa vang tren co xanh");
	 postThread.setEnable_post_thread(true);
	 postThread.setTime_post_thread(date);
	 postThread.setTitle_thread("something");
	 postThread.setPostTopic(postTopic);
	 repository.save(postThread);
	 
	 
 }
 
 @GetMapping("/insert2")
 public void onetomay() {
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	 

	 
	
	 
	 PostThread postThread23 = new PostThread();
	// postThread23.setUser_pk(2L);
	 postThread23.setContent_of_thread("hoa vang tren co xanh");
	 postThread23.setEnable_post_thread(true);
	 postThread23.setTime_post_thread(date);
	 postThread23.setTitle_thread("something");
	
	 
	 
	 Set<PostThread> postThreadsS = new HashSet<>();
	 postThreadsS.add(postThread23);
	
	 
	 
	 PostTopic postTopic = new PostTopic();
	 postTopic.setEnable_post_topic(true);
	 postTopic.setName_topic("sthing24");
	 postTopic.setPostThreads(postThreadsS);
	 repository.save(postTopic);
	 
	 
 }
}
