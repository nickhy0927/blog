package com.cako.platform.category.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
@Transactional(readOnly = false)
public class CategoryServiceImpl extends DefaultAbstractService<Category, String> implements CategoryService {
	
}
