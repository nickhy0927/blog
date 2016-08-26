package com.cako.content_deply.tribune.note.entity;

import com.cako.platform.category.entity.Category;

public class CategoryTree {
	private Category category;
	private String columnType;// 栏目类型
	private String id;

	private String name;// 栏目名称
	private String code;// 编号
	private boolean open;
	private String pId;

	public CategoryTree(Category category) {
		this.category = category.getCategory();
		this.id = category.getId();
		this.name = category.getName();
		this.pId = category.getCategory() != null ? category.getCategory().getId() : "";
		this.open = Boolean.FALSE;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
}
