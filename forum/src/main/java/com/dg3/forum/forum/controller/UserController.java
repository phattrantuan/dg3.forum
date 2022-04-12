package com.dg3.forum.forum.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.UserService;

//hungcute
//hungbaby
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserstRepository userRepository;
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<Users> listAll() {
		return service.listAll();
	}


//
//	@GetMapping("/{id}")
//	public Optional<Users> getid(@PathVariable Long id) {
//		LOGGER.error("logid");
//		return userRepository.findById(id);
//				
//	
//	}
	@PostMapping
	public Users Users(@RequestBody Users users) {
		return userRepository.save(users);
	}
	@PutMapping
	public Users UpdateUser(@RequestBody Users users) {

		return userRepository.save(users);
	}
	@DeleteMapping("{id}")
	public void a(@PathVariable Long id) {

		 userRepository.deleteById(id);
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
		LOGGER.error("logid");
        Optional<Users> users = userRepository.findById(id);
        return users.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Ok", "have users", users)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "not found user"+id,"")
                );
    }

}
