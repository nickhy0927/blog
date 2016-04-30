package com.cako.platform.role.service.impl;

import org.springframework.stereotype.Service;

import com.cako.platform.role.entity.Role;
import com.cako.platform.role.service.RoleService;
import com.orm.commons.service.impl.DefaultAbstractService;

/**
 * Created by Curtain on 2015/9/21.
 */
@Service
public class RoleServiceImpl extends DefaultAbstractService<Role, String> implements RoleService {
}
