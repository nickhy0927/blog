package com.cako.platform.category.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.platform.utils.SysContants.CategoryType;
import com.orm.commons.utils.IdEntity;

/**
 * 栏目分类
 * 
 * @author HuangYuan
 *
 */
@Entity
@Table(name = "t_p_category")
public class Category extends IdEntity {

	private String name;// 栏目名称
	private String code;// 栏目编号
	private Category category;
	private Integer type = CategoryType.COLUMN_1;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
