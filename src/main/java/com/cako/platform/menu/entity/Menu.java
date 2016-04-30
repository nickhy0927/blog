package com.cako.platform.menu.entity;

import com.cako.platform.utils.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Curtain on 2015/9/15.
 */
@Entity
@Table(name = "t_platform_menu")
public class Menu extends BaseEntity {

	private String href;
	private String name;
	private String authority;// 别名

	private Menu menu;

	@ManyToOne
	@JoinColumn(name = "p_id")
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
