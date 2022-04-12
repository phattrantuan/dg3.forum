package com.dg3.forum.forum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserstRepository userRepository;

	@Override
	public List<Users> listAll() {
		return userRepository.findAll();
	}
	/*
	 * ABC
	 * request
	 * 
	 */
	@Override
	public Users getUsers(Long id) {
		return userRepository.getById(id);
	}

}
