package com.dg3.forum.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;

@Service
public class UserService {
	@Autowired
	UserstRepository userRepository;

	public List<Users> listAll() {
		return userRepository.findAll();
	}
}
