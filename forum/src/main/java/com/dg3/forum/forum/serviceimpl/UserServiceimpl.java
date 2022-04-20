package com.dg3.forum.forum.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.AdminRepository;
import com.dg3.forum.forum.repository.UserstRepository;

import com.dg3.forum.forum.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserstRepository userRepository;

	
	
	/**
	 * get show all users
	 * @return
	 */
	@Override
	public List<Users> listAll() {
		return userRepository.findAll();
	}

	/**
	 * get all account users request id response whole information of 1 account
	 * @param id
	 * @return
	 */
	@Override
	public Users getUsers(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public boolean existById(Long id) {
		return userRepository.existsById(id);

	}

	/**
	 * find user by id
	 * @param user_pk
	 * @return
	 */
	@Override
	public Optional<Users> findById(Long user_pk) {
		return userRepository.findById(user_pk);
	}


	/**
	 * find user by Username
	 * @param username
	 * @return
	 */
    public List<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	/**
	 * save
	 * @param users
	 * @return
	 */
	@Override
	public Users save(Users users) {
		return userRepository.save(users);
	}

	/**
	 * Check number phone
	 * @param phone_number
	 * @return
	 */
	@Override
	public List<Users> checkPhone_number(String phone_number) {
		return userRepository.existByPhone_number(phone_number);
	}

	/**
	 * check email
	 * @param email
	 * @return
	 */
	@Override
	public List<Users> checkEmail(String email) {
		return userRepository.existByEmail(email);
	}

	/**
	 * delete user
	 * @param id
	 */
	@Override
	public void deleteAccount(Long id) {
		userRepository.deleteById(id);

	}

	public List<Users> findAll() {
		return userRepository.findAll();
	}

//	public User findById(int id) {
//		for (User user : listUser) {
//			if (user.getId() == id) {
//				return user;
//			}
//		}
//		return null;
//	}

//	public boolean add(User user) {
//		for (User userExist : listUser) {
//			if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
//				return false;
//			}
//		}
//		listUser.add(user);
//		return true;
//	}

//	public void delete(int id) {
//		listUser.removeIf(user -> user.getId() == id);
//	}

	public Users loadUserByUsername(String username) {

		for (Users user : userRepository.findAll()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public boolean checkLogin(Users user) {

		for (Users userExist : userRepository.findAll()) {
			if (StringUtils.equals(user.getUsername(), userExist.getUsername())
					&& StringUtils.equals(user.getPassword(), userExist.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
