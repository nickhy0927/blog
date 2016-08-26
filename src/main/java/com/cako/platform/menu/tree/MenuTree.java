package com.cako.platform.menu.tree;

import com.cako.platform.menu.entity.Menu;
import com.orm.enums.SysEnum.Display;

public class MenuTree {

	private String name;// 菜单名称
	private String id;
	private String authority;
	private boolean open;
	private String pId;
	private String displayStatus;
	private Menu menu;
	private String href;
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public MenuTree(Menu menu) {
		this.menu = menu.getMenu();
		this.id = menu.getId();
		this.name = menu.getName();
		this.authority = menu.getAuthority();
		if (menu.getDisplayStatus() == Display.DISPLAY) {
			this.displayStatus = "DISPLAY";
		} else {
			this.displayStatus = "NONE";
		}
		this.open = false;
		this.href = menu.getHref();
		this.pId = menu.getMenu() != null ? menu.getMenu().getId() : "";
	}

	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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
