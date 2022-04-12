package com.dg3.forum.forum.service;

import java.util.List;

import com.dg3.forum.forum.entity.Users;

public interface UserService {

	public List<Users> listAll();
	public Users getUsers(Long id);
}
