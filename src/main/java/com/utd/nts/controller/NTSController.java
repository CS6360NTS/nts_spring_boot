package com.utd.nts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nts")
public class NTSController {
	@GetMapping("/demo")
	public String demo() {
		return "NTS server is UP!!!";
	}
}
