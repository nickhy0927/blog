package com.cako.platform.category.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cako.platform.utils.SysContants;
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

	private Integer validStatus;

	static {
		new Category().setValidStatus(SysContants.RecordType.VALID);
	}

	public Integer getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(Integer validStatus) {
		this.validStatus = validStatus;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
