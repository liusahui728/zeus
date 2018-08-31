package org.com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/login")
	public String sayHello() {
		return "login";
	}

}
