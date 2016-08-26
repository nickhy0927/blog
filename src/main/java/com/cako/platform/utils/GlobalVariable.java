package com.cako.platform.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cako.platform.user.entity.User;

public class GlobalVariable {

	private static String  GLOBALUSER = "global_user";
	
	private static HttpSession session = null;
	
	public static void setUser(User user,HttpServletRequest request){
		if (session == null) {
			session = request.getSession();
		}
		session.setAttribute(GLOBALUSER, user);
	}
	
	public static User getUser(HttpServletRequest request){
		if (session == null) {
			session = request.getSession();
		}
		User user = (User) session.getAttribute(GLOBALUSER);
		return user;
	}
	
}
