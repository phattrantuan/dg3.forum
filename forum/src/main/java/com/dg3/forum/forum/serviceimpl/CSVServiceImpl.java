package com.dg3.forum.forum.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.util.CSVHelper;


@Service
public class CSVServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVServiceImpl.class);

	
  @Autowired
  UserstRepository repository;
  @Async
  public CompletableFuture save(MultipartFile file) {
	  final long start = System.currentTimeMillis();
    try {
      List<Users> listUsers = CSVHelper.csvToTutorials(file.getInputStream());
      LOGGER.info("Saving a list of cars of size {} records", listUsers.size());
      repository.saveAll(listUsers);
      LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
      return CompletableFuture.completedFuture(listUsers);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
  

  public ByteArrayInputStream load() {
    List<Users> listUsers = repository.findAll();
    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(listUsers);
    return in;
  }

  public List<Users> getAllUsers() {
    return repository.findAll();
  }
  
}

