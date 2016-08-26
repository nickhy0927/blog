package com.cako.platform.user.service.impl;

import com.orm.config.PageConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cako.platform.user.dao.UserDao;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.encryption.MD5Encryption;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.service.impl.DefaultAbstractService;
import com.orm.enums.SysEnum.DeleteStatus;
import com.orm.enums.SysEnum.Display;
import com.orm.enums.SysEnum.UserType;

@Service
public class UserServiceImpl extends DefaultAbstractService<User, String> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PageConfig pageConfig;

	@Override
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	/**
	 * 根据用户名和密码登录
	 */
	@Override
	public User findUserByLoginNameAndPassword(String loginName, String password) {
		User root = isRoot(loginName, password);
		if (root != null) {
			return root;
		} else {
			root = findUserByLoginName(loginName);
			if (root != null) {
				if (root.getPassword().equals(MD5Encryption.MD5(password))) {
					return root;
				}
			}
		}
		return null;
	}

	@Override
	public User get(String id) throws ServiceException {
		return userDao.findOne(id);
	}

	/**
	 * 判断是否为管理员
	 */
	@Override
	public User isRoot(String loginName, String password) {
		if (StringUtils.isNotEmpty(loginName) && loginName.equals(pageConfig.getInitUserName())) {
			if (MD5Encryption.MD5(password).equals(MD5Encryption.MD5(pageConfig.getInitPassword()))) {
				User user = new User();
				user.setDeleteStatus(DeleteStatus.NO);
				user.setDisplayStatus(Display.DISPLAY);
				user.setUserType(UserType.ADMIN);
				user.setLoginName(loginName);
				user.setPassword(MD5Encryption.MD5(password));
				return user;
			}
		}
		return null;
	}

}
