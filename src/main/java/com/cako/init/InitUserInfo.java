package com.cako.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.cako.platform.utils.SysContants;
import com.orm.commons.exception.ServiceException;

public class InitUserInfo {

	@Autowired
	public UserService userService;

	private Map<String, User> userMap;

	public Map<String, User> getUserMap() {
		return userMap;
	}

	private void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	private void initUserInfo() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			paramMap.put("status_eq", SysContants.RecordType.VALID);
			List<User> userList = userService.queryByMap(paramMap);
			Map<String, User> userMap = new HashMap<String, User>();
			for (User user : userList) {
				userMap.put(user.getLoginName(), user);
			}
			this.setUserMap(userMap);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized() {
		initUserInfo();
	}
}
