package com.cako.platform.menu.service.impl;

import org.springframework.stereotype.Service;

import com.cako.platform.menu.entity.Menu;
import com.cako.platform.menu.service.MenuService;
import com.orm.commons.service.impl.DefaultAbstractService;

/**
 * Created by Curtain on 2015/9/15.
 */
@Service
public class MenuServiceImpl extends DefaultAbstractService<Menu, String> implements MenuService {
}
