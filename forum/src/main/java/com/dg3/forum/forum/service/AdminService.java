package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Users;

public interface AdminService {
	//insert a User have role Manager/Dealer
	public boolean insertUser(Users users);
	
	//check exist id of users
		public boolean existById(Long id);
	//delete dealer when expire contract
		public boolean deleteDealer(Long id);
		
}
