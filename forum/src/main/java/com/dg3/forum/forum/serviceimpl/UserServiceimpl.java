package com.dg3.forum.forum.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserstRepository userRepository;

	/*
	 * 
	 */
	@Override
	public List<Users> listAll() {
		return userRepository.findAll();
	}

	/*
	 * get all account users request id response whole information of 1 account
	 */
	@Override
	public Users getUsers(Long id) {
		return userRepository.getById(id);
	}

	/*
	 * delete 1 account request id need delete response the account information been
	 * deleted or not
	 */
	@Override
	public boolean existById(Long id) {
		return userRepository.existsById(id);

	}

	/*
	 * get an account users request id response whole information of 1 account
	 */
	@Override
	public Optional<Users> findById(Long user_pk) {
		// TODO Auto-generated method stub
		return userRepository.findById(user_pk);
	}

	@Override
	public void deleteAccount(Long id) {

		userRepository.deleteById(id);

	}

}
