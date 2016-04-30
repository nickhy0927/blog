package com.cako.platform.user.dao;

import org.springframework.data.jpa.repository.Query;

import com.cako.platform.user.entity.User;
import com.orm.commons.service.CakoHyJpaRepostiory;

public interface UserDao extends CakoHyJpaRepostiory<User, String> {

	@Query("select u from User u where u.loginName = ?1")
	User findUserByLoginName(String loginName);

}
