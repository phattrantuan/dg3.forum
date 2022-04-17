package com.dg3.forum.forum.serviceimpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	UserstRepository userstRepository;

	/*
	 * Insert User with role is Manager or Dealer
	 */

	public boolean insertUser(Users users) {
		if (Objects.isNull(userstRepository.save(users))) {
			return false;
		};
		return true;
	}
	/*
	 * check exist of users  
	 */
	@Override
	public boolean existById(Long id) {
		return userstRepository.existsById(id);
	}
	
	/*
	 * delete user when expire contract  
	 */
	
	@Override
	public boolean deleteDealer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}