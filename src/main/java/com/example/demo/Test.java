package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {

	@RequestMapping("/TestLayout")
	public ModelAndView firstPage() {
		return new ModelAndView("TestLayout");
	}

}