package com.cako.ionic.mobile.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.mobile.service.AppUserLoginService;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.service.impl.DefaultAbstractService;
import com.orm.commons.spring.SpringContextHolder;

@Service(value = "appUserLoginService")
public class AppUserLoginServiceImpl extends DefaultAbstractService<User, String> implements AppUserLoginService {

	private static UserService userService;

	static {
		userService = SpringContextHolder.getBean(UserService.class);
	}

	public ResponseData userLogin(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		Object username = paramsMap.get("username");
		Object password = paramsMap.get("password");
		if (username != null && password != null) {
			User user = userService.findUserByLoginNameAndPassword(username.toString(), password.toString());
			if (user != null) {
				paramsMap.put("userInfo", user);
				response.setResultMap(paramsMap);
				response.setResponseCode(ResponseData.CALLCODE.SUCCESS);
				String responseMessage = "登录成功";
				response.setResponseMessage(responseMessage);
			} else {
				response.setResponseCode(ResponseData.CALLCODE.FAIL);
				String responseMessage = "登录失败，用户名或密码错误";
				response.setResponseMessage(responseMessage);
			}
		} else {
			response.setResponseCode(ResponseData.CALLCODE.FAIL);
			String responseMessage = "登录失败，用户名或密码错误";
			response.setResponseMessage(responseMessage);
		}
		response.setResultMap(paramsMap);
		return response;
	}

	public ResponseData updateUserTag(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		Object username = paramsMap.get("username");
		try {
			if (username != null) {
				User user = userService.findUserByLoginName(username.toString());
				if (user != null && paramsMap.get("userTag") != null) {
					String userTag = paramsMap.get("userTag").toString();
					user.setUserTag(userTag );
					userService.save(user);
					paramsMap.put("userInfo", user);
					response.setResultMap(paramsMap);
					response.setResponseCode(ResponseData.CALLCODE.SUCCESS);
					String responseMessage = "启动推送服务成功";
					response.setResponseMessage(responseMessage);
				} else {
					response.setResponseCode(ResponseData.CALLCODE.FAIL);
					String responseMessage = "启动推送服务失败";
					response.setResponseMessage(responseMessage);
				}
			} else {
				response.setResponseCode(ResponseData.CALLCODE.FAIL);
				String responseMessage = "启动推送服务失败";
				response.setResponseMessage(responseMessage);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			response.setResponseCode(ResponseData.CALLCODE.FAIL);
			String responseMessage = "启动推送服务失败";
			response.setResponseMessage(responseMessage);
		}
		response.setResultMap(paramsMap);
		return response;
	}
}
