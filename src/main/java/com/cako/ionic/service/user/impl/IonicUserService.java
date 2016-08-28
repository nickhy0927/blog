package com.cako.ionic.service.user.impl;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.service.user.IIonicService;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.service.impl.DefaultAbstractService;
import com.orm.commons.spring.SpringContextHolder;

@Service
public class IonicUserService extends DefaultAbstractService<User, String> implements IIonicService {

	private static UserService userService;

	static {
		if (userService == null) {
			userService = SpringContextHolder.getBean(UserService.class);
		}
	}

	@Override
	public ResponseData findUserByLoginNameAndPassword(Map<String, Object> paramsMap) {
		return null;
	}

	@Override
	public ResponseData userLogin(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		Object username = paramsMap.get("username");
		Object password = paramsMap.get("password");
		if (username != null && password != null) {
			User user = userService.findUserByLoginNameAndPassword(username.toString(), password.toString());
			if (user != null) {
				user.setPassword(null);
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

	@Override
	public ResponseData updateUserTag(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		try {
			User user = new User();
			BeanUtils.populate(user, paramsMap);
			user = userService.save(user);
			response.setResponseMessage("操作成功");
			response.setResponseCode(ResponseData.CALLCODE.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode(ResponseData.CALLCODE.FAIL);
			String responseMessage = "操作失败";
			response.setResponseMessage(responseMessage);
		}
		return response;
	}
}
