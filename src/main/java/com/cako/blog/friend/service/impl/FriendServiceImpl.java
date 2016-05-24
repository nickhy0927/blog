package com.cako.blog.friend.service.impl;

import org.springframework.stereotype.Service;

import com.cako.blog.friend.service.FriendService;
import com.cako.platform.user.entity.User;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
public class FriendServiceImpl extends DefaultAbstractService<User, String> implements FriendService {
	
}
