package com.dg3.forum.forum.controller;

import javax.servlet.http.HttpServletRequest;
import com.dg3.forum.forum.serviceimpl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.serviceimpl.AdminServiceImpl;
import com.dg3.forum.forum.serviceimpl.CSVServiceImpl;
import com.dg3.forum.forum.serviceimpl.JwtServiceImpl;




@RestController
@RequestMapping("/api/v1/")
public class LoginController {
	private static org.apache.log4j.Logger LOGGER = Logger.getLogger(LoginController.class);
	
@Autowired
AdminServiceImpl adminServiceImpl;
	
	
  @Autowired
  CSVServiceImpl fileService;
  
  @Autowired 
  JwtServiceImpl jwtServiceImpl;
  @Autowired
  UserServiceImpl userServiceimpl;
  
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
}
  
  
  
