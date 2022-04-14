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

    /**
     * Show list all
     *
     * @return
     */
    @Override
    public List<Users> listAll() {
        return userRepository.findAll();
    }

    /**
     * Get all account users request id response whole information of 1 account
     *
     * @param id
     * @return
     */
    @Override
    public Users getUsers(Long id) {
        return userRepository.getById(id);
    }

    /**
     * Delete 1 account request id need delete response the account information been
     * Deleted or not
     *
     * @param id
     * @return
     */
    @Override
    public boolean existById(Long id) {
        return userRepository.existsById(id);

    }

    /**
     * Find by id
     *
     * @param user_pk
     * @return
     */
    @Override
    public Optional<Users> findById(Long user_pk) {
        return userRepository.findById(user_pk);
    }

    /**
     * Find by Username
     *
     * @param username
     * @return
     */
    @Override
    public List<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Save
     *
     * @param users
     * @return
     */
    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    /**
     * Check phone number
     *
     * @param phone_number
     * @return
     */
    @Override
    public List<Users> checkPhone_number(String phone_number) {
        return userRepository.existByPhone_number(phone_number);
    }

    /**
     * Check email
     *
     * @param email
     * @return
     */
    @Override
    public List<Users> checkEmail(String email) {
        return userRepository.existByEmail(email);
    }

    /**
     * Delete by id
     *
     * @param id
     */
    @Override
    public void deleteAccount(Long id) {
        userRepository.deleteById(id);

    }

}
