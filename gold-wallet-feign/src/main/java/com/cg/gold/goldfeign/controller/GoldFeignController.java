package com.cg.gold.goldfeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/feign")
public class GoldFeignController {

	@GetMapping("/welcome")
	public String getWelcome() {
		return "Warm Welcome User!";
	}

}
