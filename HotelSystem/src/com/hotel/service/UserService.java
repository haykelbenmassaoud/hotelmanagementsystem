package com.hotel.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserService {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

}
