package com.cako.platform.role.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cako.platform.utils.BaseEntity;

/**
 * Created by Curtain on 2015/9/21.
 */
@Entity
@Table(name = "t_platform_role")
public class Role extends BaseEntity {
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
}
