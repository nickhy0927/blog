package com.cako.basic.topic.column.tree;

import com.cako.basic.topic.column.entity.Column;
import com.orm.enums.SysEnum.ColumnType;
import com.orm.enums.SysEnum.Display;

public class ColumnTree {
	private Column column;
	private String columnType;// 栏目类型
	private String displayStatus;
	private String id;

	private String name;// 栏目名称
	private String number;// 编号
	private boolean open;
	private String pId;

	public ColumnTree(Column column) {
		this.column = column.getColumn();
		if (column.getColumnType() == ColumnType.CMS) {
			this.columnType = "CMS";
		} else if (column.getColumnType() == ColumnType.NOTE) {
			this.columnType = "NOTE";
		} else {
			this.columnType = "COMMON";
		}
		if (column.getDisplayStatus() == Display.DISPLAY) {
			this.displayStatus = "DISPLAY";
		} else {
			this.displayStatus = "NONE";
		}
		this.id = column.getId();
		this.name = column.getName();
		this.number = column.getNumber();
		this.pId = column.getColumn() != null ? column.getColumn().getId() : "";
		this.open = Boolean.FALSE;
	}

	public Column getColumn() {
		return column;
	}

	public String getColumnType() {
		return columnType;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getpId() {
		return pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
}
