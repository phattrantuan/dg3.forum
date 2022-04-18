package com.dg3.forum.forum.service;

import java.util.List;
import java.util.Optional;

import com.dg3.forum.forum.entity.PostTopic;
import com.dg3.forum.forum.entity.Users;

public interface UserService {
    /**
     * Get all information account users
     * @return
     */
    List<Users> listAll();

    /**
     * Get only account user
     * @param id
     * @return
     */
    Users getUsers(Long id);

    /**
     * Check exist account account user
     *
     * @param id
     * @return
     */
    boolean existById(Long id);

    /**
     * Delete account account user
     *
     * @param id
     */
    void deleteAccount(Long id);

    /**
     * Get only account user
     *
     * @param user_pk
     * @return
     */
    Optional<Users> findById(Long user_pk);

    /**
     * Find by username
     *
     * @param username
     * @return
     */
    List<Users> findByUsername(String username);

    /**
     * Save
     *
     * @param users
     * @return
     */
    Users save(Users users);

    /**
     * Check phone number
     *
     * @param phone_number
     * @return
     */
    List<Users> checkPhone_number(String phone_number);

    /**
     * Check email
     *
     * @param email
     * @return
     */
    List<Users> checkEmail(String email);

}
