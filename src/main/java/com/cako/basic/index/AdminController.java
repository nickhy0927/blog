package com.cako.basic.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/adminIndex.html")
	public ModelAndView adminIndex() {
		ModelAndView view = new ModelAndView("admin/index");
		return view;
	}

	@RequestMapping(value = "/home.html")
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("admin/home");
		return view;
	}

	@RequestMapping(value = "/intoLogin.html")
	public ModelAndView intoLogin() {
		ModelAndView view = new ModelAndView("login/login");
		return view;
	}
}
