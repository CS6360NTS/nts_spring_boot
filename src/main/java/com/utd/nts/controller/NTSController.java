package com.utd.nts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;
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
	public NtsUserResponse getAllUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/user")
	public NtsTradeUserResponse getUserWithId(@RequestParam int clientId) {
		return userService.getUserTraderById(clientId);
	}

	@PostMapping("/addUser")
	public boolean addUser(NtsUserEntity user) {
		userService.addUser(user);
		return true;
	}
}
