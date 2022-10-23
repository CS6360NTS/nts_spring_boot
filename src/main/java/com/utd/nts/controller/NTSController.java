package com.utd.nts.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.service.UserService;

@RestController
@RequestMapping(value = "/nts")
public class NTSController {

	@Autowired
	UserService userService;

	@GetMapping("/demo")
	public String demo() {
		return "NTS server is UP!!!";
	}

	@GetMapping("/users")
	public Collection<NtsUserEntity> getAllUsers() {
		return userService.getUsers();
	}

	@PostMapping("/addUser")
	public boolean addUser(NtsUserEntity user) {
		userService.addUser(user);
		return true;
	}
}
