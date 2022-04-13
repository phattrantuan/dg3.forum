package com.dg3.forum.forum.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private UserstRepository repository;


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
     * @param username
     * @return
     */
    @GetMapping("/list/{username}")
    public ResponseEntity<Message> findByUserName(@PathVariable String username) {
        LOGGER.error("findByUserName");
        List<Users> usersList = service.findByUsername(username);
        return usersList.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Không tìm thấy!", "Không tìm thấy người dùng" + " " + username+ "này!", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Tìm thấy!", "Người dùng:", usersList)
                );
    }

    /**
     * insert check phone
     * @param users
     * @return
     */
    @PostMapping("/insert")
    ResponseEntity<Message> insertProduct(@RequestBody Users users) {
        //2 products must not have the same phone number !
        List<Users> foundPhoneNumber = repository.existByPhone_number(users.getPhone_number().trim());
        if(foundPhoneNumber.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Message("Thất bại", "Số điện thoại đã tồn tại!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công", "Thêm người dùng thành công!", service.save(users))
        );
    }

    @DeleteMapping("/{user_pk}")
    ResponseEntity<Message> deleteProduct(@PathVariable Long user_pk) {

        if (service.existById(user_pk)) {
            service.deleteAccount(user_pk);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Message("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Message("failed", "Cannot find product to delete", "")
        );
    }



}
