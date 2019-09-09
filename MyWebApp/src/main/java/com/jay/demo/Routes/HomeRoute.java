package com.jay.demo.Routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeRoute {
	
	@RequestMapping("home")
	public String home() {

		return "index.html";
		
	}

}
