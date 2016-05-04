package com.cako.platform.category.dao;

import org.springframework.stereotype.Repository;

import com.cako.platform.category.entity.Category;
import com.orm.commons.service.CakoHyJpaRepostiory;

@Repository
public interface CategoryDao extends CakoHyJpaRepostiory<Category, String>{

}
