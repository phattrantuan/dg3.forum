package com.dg3.forum.forum.controller;

import java.util.Objects;

import com.dg3.forum.forum.config.JwtTokenUtil;
import com.dg3.forum.forum.dto.JwtRequestDTO;
import com.dg3.forum.forum.dto.JwtResponseDTO;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	private static org.apache.log4j.Logger LOGGER = Logger.getLogger(JwtAuthenticationController.class);
	private static final String AUTH_HEADER = null;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private UserService userService;

	/**
	 * Login application
	 * @param authenticationRequest
	 * @return login application
	 * @throws Exception
	 */
	@RequestMapping(value = {"/authenticate", "/signin"}, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) throws Exception {
		LOGGER.info("Thành công!");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		Users users = userService.findByEmail(authenticationRequest.getUsername());

		/*
		* Nếu tài khoản có Ban_account là false và enable_users là true thì tạo token
		* */
		if(users.isBan_account() == false && users.isEnable_users() == true){
			final UserDetails userDetails = jwtInMemoryUserDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String token = "Bearer " + jwtTokenUtil.generateToken(userDetails);

			Authentication authentication =
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok(new JwtResponseDTO(token));
		} else {
			/*
			* nếu ban_account là true thì thông báo là tài khoản bị khóa
			* */
			if(users.isBan_account() == true){
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
						new Message("Failed", "Account is blocked", "")
				);
			}
			/*
			* nếu enable_user là false  thì thông báo tài khoản bị vô hiệu hóa
			* */
			else if(users.isEnable_users() == false){
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
						new Message("Failed", "Account has been deactivated", "")
				);
			}
			/*
			* còn lại là thông báo tài khoản bị vô hiệu hóa
			* */
			else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
						new Message("Failed", "Account disabled", "")
				);
			}
		}


	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * logout
	 * @param request and response
	 * @return logout
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
}
