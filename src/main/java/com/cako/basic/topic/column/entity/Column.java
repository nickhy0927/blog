package com.cako.basic.topic.column.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.platform.utils.BaseEntity;
import com.orm.enums.SysEnum;
import com.orm.enums.SysEnum.ColumnType;

@Entity
@Table(name = "t_topic_column")
public class Column extends BaseEntity {

	private Column column;
	private SysEnum.ColumnType columnType = ColumnType.NOTE;// 栏目类型
	private String name;// 栏目名称
	private String number;// 编号
	private Integer parent = 2;

	@ManyToOne
	@JoinColumn(name = "column_id")
	public Column getColumn() {
		return column;
	}

	public SysEnum.ColumnType getColumnType() {
		return columnType;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setColumnType(SysEnum.ColumnType columnType) {
		this.columnType = columnType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
}
