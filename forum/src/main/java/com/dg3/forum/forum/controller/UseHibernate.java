package com.dg3.forum.forum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg3.forum.forum.dto.Usersdto;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UserstRepository;
@RestController
@RequestMapping("/test")
public class UseHibernate {
	
	@Autowired
	private UserstRepository repo;
	


	@GetMapping("/{a}")
	public Users listAll(@PathVariable Long a) {
		return repo.findRoomByUserId(a);
	}
	@GetMapping
	public List<Usersdto> listAlla() {
		List<Usersdto> listdto = new ArrayList<Usersdto>();
		 for (Users users :  repo.findAll()){
			 listdto.add(new Usersdto(users));
	        }
		
		return  listdto;
	}
	
//	@GetMapping("/a")
//	public List<Users> listAll1() {
//		return service.listAll();
//	}
}
