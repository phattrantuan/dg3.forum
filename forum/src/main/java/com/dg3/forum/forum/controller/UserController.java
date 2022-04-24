package com.dg3.forum.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.dto.UserAndToken;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.service.JwtService;
import com.dg3.forum.forum.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;
    
    @Autowired
    private JwtService jwtService;
    
    
  

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
    ResponseEntity<Message> insertUser(@RequestBody @Valid Users users) {
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
    @PostMapping("/sign-up")
    ResponseEntity<Message> signUp(@RequestBody @Valid Users users) {
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
        
        String encoded = new BCryptPasswordEncoder().encode(users.getPassword());
        users.setPassword(encoded);
       
        
        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("Thành công", "Thêm người dùng thành công!", service.save(users))
        );
    }

    
    /**
     * Login
     * @param request
     * @param user
     * @return
     */
 	@PostMapping( "/login")
 	public ResponseEntity<UserAndToken> login(HttpServletRequest request, @RequestBody Users user) {
 		String result = "";
 		HttpStatus httpStatus = null;
 		UserAndToken userAndToken = new UserAndToken();
 		 System.out.print(user.getUsername() + user.getPassword());
 		try {
 			
 				result = jwtService.generateTokenLogin(user.getUsername());
 				List<Users> userData = service.findByUsername(user.getUsername());
 				Users u = userData.get(0);
 				 				
 				if(!new BCryptPasswordEncoder().matches( user.getPassword(),u.getPassword()) || u==null) {
 					httpStatus = HttpStatus.BAD_REQUEST;
 					
 				}
 					 else {
 					userAndToken.setUser(userData.get(0));
 	 				userAndToken.setToken(result);

 	 				httpStatus = HttpStatus.BAD_REQUEST;
 				}
 				
 				
 			
 		} catch (Exception ex) {
 			// System.out.print(ex);
 			result = "Server Error";
 			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
 		}
 		return new ResponseEntity<UserAndToken>(userAndToken, httpStatus);
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
    
/**
 * Export error validation  
 * @param ex
 * @return
 */
      @ResponseStatus(HttpStatus.BAD_REQUEST)
      @ExceptionHandler(MethodArgumentNotValidException.class)
      public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach((error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
          });
          return errors;
      }
}
