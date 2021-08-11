package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("/BasicLayout")
	public ModelAndView firstPage() {
		return new ModelAndView("BasicLayout");
	}

}