package com.dg3.forum.forum.service;

import java.util.List;

import com.dg3.forum.forum.entity.Users;

public interface AdminService {
	/**
	 * Insert User with role is Manager or Dealer(not through dto)
	 * 
	 * @param Users
	 * @return true or false
	 */
	public boolean insertUser(Users users);

	/**
	 * find id Users exist
	 * @param id
	 * @return true or false
	 */
	public boolean existById(Long id);




	/**
	 * Insert User with role is Manager or Dealer( through dto)
	 * 
	 * @param Users
	 * @return 0 or 1
	 */
	public int insertUserManagerOrDealer(Users users);

	/**
	 * block account
	 * 
	 * @param User_pk
	 * @return account blocked
	 */
	public void blockAccount(Long user_pk);
	
	/**
	 * delete account
	 * 
	 * @param User_pk
	 * @return account deleted
	 */
	void deleteAccount(Long id);
	
	/**
	 * disable account when expire contract
	 * 
	 * @param 
	 * @return enable_users= false
	 */
	void disableAccountExpireContract();
	
	/**
	 * get All Account Expire today
	 * 
	 * @param 
	 * @return list account Expire today
	 */
	List<Users> getAllAccoutExpiretoday();
}
