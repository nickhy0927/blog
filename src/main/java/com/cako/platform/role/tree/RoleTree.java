package com.cako.platform.role.tree;

import com.cako.platform.role.entity.Role;

public class RoleTree {

	private String name;// 菜单名称
	private String id;
	private String code;
	private boolean open;
	private String pId;

	public RoleTree(Role role) {
		this.id = role.getId();
		this.name = role.getName();
		this.code = role.getCode();
		this.open = false;
		this.pId = "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
