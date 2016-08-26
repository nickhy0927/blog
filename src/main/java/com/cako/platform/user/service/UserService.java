package com.cako.platform.user.service;

import com.cako.platform.user.entity.User;
import com.orm.commons.service.BaseService;

public interface UserService extends BaseService<User, String> {

	public User findUserByLoginName(String loginName);

	public User findUserByLoginNameAndPassword(String loginName, String password);

	public User isRoot(String loginName, String password);
}
