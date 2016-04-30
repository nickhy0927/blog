package com.cako.basic.out;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;

@Controller
public class OutLoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/outLink/outLogin")
	public String login(HttpServletResponse response,HttpServletRequest request){
		String loginName = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.findUserByLoginNameAndPassword(loginName, password);
		if (user != null) {
			
		}
		return "";
	}
}
