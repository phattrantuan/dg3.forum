package com.dg3.forum.forum.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dg3.forum.forum.dto.UserRegisterDTO;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dg3.forum.forum.entity.Message;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

   private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private UserService service;


    /**
     * Show all user
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Message> listAll() {
        LOGGER.error("listAll");
        List<Users> users = service.listAll();
        return users.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Thất bại!", "Không thực hiện được!", "")
                )
                : ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công!", "Danh sách người dùng:", users)
        )
                ;
    }

    /**
     * Find by id
     *
     * @param user_pk
     * @return
     */
    @GetMapping("/{user_pk}")
    public ResponseEntity<Message> findById(@PathVariable Long user_pk) {
        LOGGER.error("findById");
        Optional<Users> users = service.findById(user_pk);
        return users.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Thành công!", "Thông tin người dùng với id " + user_pk+ ":", users)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Thất bại!", "Không tìm thấy" + user_pk, "")
                );
    }

    /**
     * Show list username
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
                        new Message("Không tìm thấy!", "Không tìm thấy người dùng"  + username + "này!", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("Tìm thấy!", "Thông tin người dùng với tên " + username +":" , usersList)
                );
    }

    /**
     * Insert check phone and check email
     *
     * @param users
     * @return
     */
    @PostMapping("/insert")
    ResponseEntity<Message> insertUser(@RequestBody Users users) {
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
     * Update, upsert = update if found, otherwise insert
     *
     * @param newUser
     * @param user_pk
     * @return
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

    /**
     * Delete id
     *
     * @param user_pk
     * @return
     */
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

   @PostMapping("/image")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@RequestParam String email,
                       @RequestParam String password,
                       @RequestParam String username,
                       @RequestParam String role,
                       @RequestParam String phone_number,
                       @RequestParam String address,
                       @RequestParam Date date_of_birth,
                       @RequestParam boolean ban_account,
                       @RequestParam String description,
                       @RequestParam Date created_date,
                       @RequestParam Date expire,
                       @RequestParam boolean enable_users,
                       @RequestParam MultipartFile image) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        Users users = new Users();
        users.setEmail(email);
        users.setPassword(password);
        users.setUsername(username);
        users.setRole(role);
        users.setPhone_number(phone_number);
        users.setAddress(address);
        users.setDate_of_birth(date_of_birth);
        users.setBan_account(ban_account);
        users.setImg_avatar(imagePath.resolve(image.getOriginalFilename()).toString());
        users.setDescription(description);
        users.setCreated_date(created_date);
        users.setExpire(expire);
        users.setEnable_users(enable_users);
        return service.save(users);
    }

    @PostMapping("/register")
    ResponseEntity<Message> insertDocument(@RequestBody UserRegisterDTO userRegisterDTO) {
        List<Users> foundPhoneNumber = service.checkPhone_number(userRegisterDTO.getPhone_number().trim());
        List<Users> foundEmail = service.checkEmail(userRegisterDTO.getEmail().trim());
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
                new Message("Thành công", "Thêm người dùng thành công!", service.createUser(userRegisterDTO))
        );
    }
}
