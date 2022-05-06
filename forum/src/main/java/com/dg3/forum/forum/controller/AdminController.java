package com.dg3.forum.forum.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dg3.forum.forum.config.WebSecurityConfig;
import com.dg3.forum.forum.dto.UserAdminOrDealerDTO;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.serviceimpl.AdminServiceImpl;
import com.dg3.forum.forum.serviceimpl.CSVServiceImpl;
import com.dg3.forum.forum.serviceimpl.PostThreadServiceImpl;
import com.dg3.forum.forum.serviceimpl.UserServiceImpl;
import com.dg3.forum.forum.util.CSVHelper;

@RestController

@RequestMapping("/api/v1/admin")
public class AdminController {
	private static org.apache.log4j.Logger LOGGER = Logger.getLogger(AdminController.class);
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	@Autowired
	AdminServiceImpl adminServiceImpl;

	@Autowired
	CSVServiceImpl fileService;

	@Autowired
	UserServiceImpl userServiceimpl;
	@Autowired
	PostThreadServiceImpl postThreadServiceImpl;

	@Autowired
	WebSecurityConfig webSecurityConfig;

	/**
	 * insert list users have role Dealer and Manager
	 * 
	 * @param file
	 * @return list users
	 */
	@PostMapping("/csv/upload")
	public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
						.path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new Message("OK", message, fileDownloadUri));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message("Faild", message, ""));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("not import", message, ""));
	}

	/**
	 * get all information users
	 * @return list Users
	 */
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers() {
		try {
			List<Users> users = userServiceimpl.listAll();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return file contain information of user
	 */
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		InputStreamResource file = new InputStreamResource(fileService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	
/**
 * insert User Dealer Or Manager
 * @param userAdminOrDealerdto
 * @return information inserted
 */
	@PostMapping("/insertUserDealerOrManager")
	ResponseEntity<Message> insertUserDealerOrManager(@RequestBody @Valid UserAdminOrDealerDTO userAdminOrDealerdto) {
		LOGGER.error("save dealer or manager");
		List<Users> foundPhoneNumber = userServiceimpl.checkPhone_number(userAdminOrDealerdto.getPhone_number().trim());
		List<Users> foundEmail = userServiceimpl.checkEmail(userAdminOrDealerdto.getEmail().trim());
		if (foundEmail.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new Message("Faild", " This email existed!", ""));
		}
		if (foundPhoneNumber.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new Message("Faild", "Phone number existed!", ""));
		}

		userAdminOrDealerdto
				.setPassword(webSecurityConfig.passwordEncoder().encode(userAdminOrDealerdto.getPassword()));
		int check = adminServiceImpl.insertUserManagerOrDealer(new Users(userAdminOrDealerdto));
		return check != 0
				? ResponseEntity.status(HttpStatus.CREATED)
						.body(new Message("Ok", "insert cussess", userAdminOrDealerdto))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Fail", "insert fail", ""));
	}



/**
 * block account
 * @param user_pk
 * @return block account
 */
	@PutMapping(value = "/block/{user_pk}")
	ResponseEntity<Message> blockUsers(@PathVariable Long user_pk) {
		if (adminServiceImpl.existById(user_pk)) {
			adminServiceImpl.blockAccount(user_pk);
			return ResponseEntity.status(HttpStatus.OK).body(new Message("success!", "Blocked", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Faild!", "not find users !", ""));
	}


	/**
	 * approved Post of User
	 * @param thread_pk
	 * @return approved Post of User
	 */
	@PutMapping(value = "/approved/{thread_pk}")
	ResponseEntity<Message> approvedPost(@PathVariable Long thread_pk) {
		if (!Objects.isNull(postThreadServiceImpl.checkExistByThread_pk(thread_pk))) {
			postThreadServiceImpl.approvedPost(thread_pk);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new Message("success!", "Approved", postThreadServiceImpl.getAnPostThrest(thread_pk)));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Faild!", "not find thread !", ""));
	}

	/**
	 * delete account users
	 * @param user_pk
	 * @return deleted account
	 */
	@DeleteMapping(value = "/deleteAccount/{user_pk}")
	ResponseEntity<Message> deleteAccount(@PathVariable Long user_pk) {
		if (adminServiceImpl.existById(user_pk)) {
			adminServiceImpl.deleteAccount(user_pk);

			return ResponseEntity.status(HttpStatus.OK).body(new Message("success!", "deleted", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Faild!", "not find users !", ""));
	}

	
	/**
	 * Disable account when expire 
	 * @return account disabled
	 */
	@PutMapping(value = "/disableAccount")
	ResponseEntity<Message> disableAccount() {
		if (adminServiceImpl.getAllAccoutExpiretoday().size() > 0) {
			adminServiceImpl.disableAccountExpireContract();
			return ResponseEntity.status(HttpStatus.OK).body(new Message("success!", "Disable account expire today",
					adminServiceImpl.getAllAccoutExpiretoday()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Message("Faild!", "not have account expire today !", ""));
	}

	// export error validation
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

	// export error when sync
//	  private static Function<Throwable, ResponseEntity<? extends List<Users>>> handleGetCarFailure = throwable -> {
//	      LOGGER.error("Failed to read records: {}", throwable);
//	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	  };
//	
}
