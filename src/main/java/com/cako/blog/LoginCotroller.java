package com.cako.blog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.cako.platform.utils.BeanToMapUtil;
import com.orm.commons.encryption.MD5Encryption;
import com.orm.commons.utils.JsonMapper;
import com.orm.enums.SysEnum.UserType;



@Controller
@RequestMapping(value = "/blog")
public class LoginCotroller {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login")
	public void login(HttpServletRequest request) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		User user = userService.findUserByLoginNameAndPassword(loginName, password);
		System.out.println(user);
		
	}
	
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public void register(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) BeanToMapUtil.getBeenObjectByRequest(new User(), request);
			user.setPassword(MD5Encryption.MD5(user.getPassword()));
			user.setUserType(UserType.GENERAL);
			System.out.println(user);
			user.setResources("2");
			userService.save(user);
			map.put("info", "新增成功");
			map.put("status", "y");
		}  catch (Exception e) {
			e.printStackTrace();
			map.put("info", "新增失败");
			map.put("status", "n");
		} finally {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(new JsonMapper().toJson(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
