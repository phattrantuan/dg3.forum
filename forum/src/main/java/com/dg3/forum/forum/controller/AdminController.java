package com.dg3.forum.forum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.dg3.forum.forum.serviceimpl.UserServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dg3.forum.forum.dto.UserAdminOrDealerdto;
import com.dg3.forum.forum.dto.Usersdto;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.serviceimpl.AdminServiceImpl;
import com.dg3.forum.forum.serviceimpl.CSVServiceImpl;
import com.dg3.forum.forum.serviceimpl.JwtServiceImpl;
import com.dg3.forum.forum.util.CSVHelper;



@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
@Autowired
AdminServiceImpl adminServiceImpl;
	
	
  @Autowired
  CSVServiceImpl fileService;
  
  @Autowired 
  JwtServiceImpl jwtServiceImpl;
  @Autowired
  UserServiceimpl userServiceimpl;

  @PostMapping("/csv/upload")
  public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new Message("OK",message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message("Faild",message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("not import",message,""));
  }

  @GetMapping("/users")
  public ResponseEntity<List<Users>> getAllTutorials() {
    try {
      List<Users> users = fileService.getAllUsers();

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());
    
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
  
  
  
  
  
  @PostMapping("/insertmanager")
  public ResponseEntity<List<Users>> insertUserManagers() {
    try {
      List<Users> users = fileService.getAllUsers();

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  
  
  
  @PostMapping("/insert")
  ResponseEntity<Message> deleteProduct(@RequestBody Users users) {
	        LOGGER.error("save dealer or manager");
	      boolean user = adminServiceImpl.insertUser(users);
	        return user ?
	                ResponseEntity.status(HttpStatus.OK).body(
	                        new Message("Ok", "insert cussess", users)
	                ) :
	                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                        new Message("Fail", "insert fail" ,  "")
	                );
	    }
  
  
  @PostMapping("/insertUserDealerOrManager")
  ResponseEntity<Message> insertUserDealerOrManager(@RequestBody UserAdminOrDealerdto userAdminOrDealerdto) {
	        LOGGER.error("save dealer or manager");
	      int check = adminServiceImpl.insertUserManagerOrDealer(new Users(userAdminOrDealerdto));
	        return check!=0 ?
	                ResponseEntity.status(HttpStatus.OK).body(
	                        new Message("Ok", "insert cussess", userAdminOrDealerdto)
	                ) :
	                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                        new Message("Fail", "insert fail" ,  "")
	                );
	    }
  

  
  
  
  
  
  
  
  
  
  
  
  @DeleteMapping("/{user_pk}")
  ResponseEntity<Message> deleteProduct(@PathVariable Long user_pk) {
      if (adminServiceImpl.existById(user_pk)) {
         
          return ResponseEntity.status(HttpStatus.OK).body(
                  new Message("Thành công!", "Xóa thành công!", "")
          );
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              new Message("Thất bại!", "Không tìm thấy người dùng này để xóa!", "")
      );
  }
  
  
  
  
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Users user) {
		String result = "";
		HttpStatus httpStatus = null;
		try {
			if (userServiceimpl.checkLogin(user)) {
				result = jwtServiceImpl.generateTokenLogin(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = "Wrong userId and password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, httpStatus);
	}
  
  
  
  
  
  
  
  
  
  
  
  



//
//	@GetMapping("/{a}")
//	public Users listAll(@PathVariable Long a) {
//		return repo.findRoomByUserId(a);
//	}

  
  
}

  
  
  
  
  
  
  
  
  
  
  
  
  



