package com.orm.enums;

import org.apache.commons.lang3.StringUtils;

public class SysEnum {

	/**
	 * @描述： 栏目所属的类型
	 * 
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:36:34
	 */
	public static enum ColumnType {
		// cms 模块
		CMS,
		// 普通公用的木块
		COMMON,
		// 论坛所属的模块
		NOTE;
	}

	public static ColumnType getColumnType(String columnType) {
		ColumnType type = null;
		if (StringUtils.equals(columnType, "CMS")) {
			type = ColumnType.CMS;
		} else if (StringUtils.equals(columnType, "COMMON")) {
			type = ColumnType.COMMON;
		} else if (StringUtils.equals(columnType, "NOTE")) {
			type = ColumnType.NOTE;
		}
		return type;
	}

	/**
	 * @描述：删除状态
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:44:16
	 */
	public static enum DeleteStatus {
		// 正常
		NO,
		// 删除
		YES;
	}

	public static enum Display {
		// 显示
		DISPLAY,
		// 不显示
		NONE;
	}

	public static enum State {
		TRUE, FALSE;
	}

	/**
	 * 用户的状态
	 *
	 * @描述：INIT 初始状态，LOCKED 锁定，NORMAL 普通状态
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:07:53
	 */
	public static enum Status {
		INIT, LOCKED, NORMAL;
	}

	/**
	 * 用户的类型
	 *
	 * @描述：ADMIN 管理员; GENERAL 普通用户;MEMBER 普通会员;LEAGUER_MEMBER 高级会员
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:08:34
	 */
	public static enum UserType {
		ADMIN, GENERAL, LEAGUER_MEMBER, MEMBER;
	}
}
