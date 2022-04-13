package com.dg3.forum.forum.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Message> listAll() {
        LOGGER.error("listAll");
        List<Users> users = service.listAll();
        return users.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "not found user", "")
                )
                : ResponseEntity.status(HttpStatus.OK).body(
                new Message("Ok", "have users", users)
        )
                ;
    }


    @GetMapping("/{user_pk}")
    public ResponseEntity<Message> findById(@PathVariable Long user_pk) {
        LOGGER.error("findById");
        Optional<Users> users = service.findById(user_pk);
        return users.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Ok", "have users", users)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "not found user" + user_pk, "")
                );
    }

    /**
     * show list username
     *
     * @param username
     * @return
     */
    @GetMapping("/list/{username}")
    public ResponseEntity<Message> findByUserName(@PathVariable String username) {
        LOGGER.error("findByUserName");
        List<Users> usersList = service.findByUsername(username);
        return usersList.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Không tìm thấy!", "Không tìm thấy người dùng" + " " + username + "này!", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Tìm thấy!", "Người dùng:", usersList)
                );
    }

    /**
     * insert check phone and check email
     *
     * @param users
     * @return
     */
    @PostMapping("/insert")
    ResponseEntity<Message> insertProduct(@RequestBody Users users) {
        //2 products must not have the same phone number and email !
        List<Users> foundPhoneNumber = service.checkPhone_number(users.getPhone_number().trim());
        List<Users> foundEmail = service.checkEmail(users.getEmail().trim());
        if (foundEmail.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Message("Thất bại", "Email này đã tồn tại!", "")
            );
        }
        if (foundPhoneNumber.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Message("Thất bại", "Số điện thoại đã tồn tại!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công", "Thêm người dùng thành công!", service.save(users))
        );
    }

    /**
     * update, upsert = update if found, otherwise insert
     */
    @PutMapping("/{user_pk}")
    ResponseEntity<Message> updateUser(@RequestBody Users newUser, @PathVariable Long user_pk) {
        Users updateUser = service.findById(user_pk)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setUsername(newUser.getUsername());
                    user.setRole(newUser.getRole());
                    user.setPhone_number(newUser.getPhone_number());
                    user.setAddress(newUser.getAddress());
                    user.setDate_of_birth(newUser.getDate_of_birth());
                    user.setBan_account(newUser.isBan_account());
                    user.setImg_avatar(newUser.getImg_avatar());
                    user.setDescription(newUser.getDescription());
                    user.setCreated_date(newUser.getCreated_date());
                    user.setExpire(newUser.getExpire());
                    user.setEnable_users(newUser.isEnable_users());
                    return service.save(user);
                }).orElseGet(() -> {
                    newUser.setUser_pk(user_pk);
                    return service.save(newUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công!", "Cập nhập thành!", updateUser)
        );
    }

    @DeleteMapping("/{user_pk}")
    ResponseEntity<Message> deleteProduct(@PathVariable Long user_pk) {

        if (service.existById(user_pk)) {
            service.deleteAccount(user_pk);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("Thành công!", "Xóa thành công!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Message("Thất bại!", "Không tìm thấy người dùng này để xóa!", "")
        );
    }

}
