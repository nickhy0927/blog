package com.cako.basic.tribune.note.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.basic.topic.column.entity.Column;
import com.cako.platform.user.entity.User;
import com.cako.platform.utils.BaseEntity;

/**
 * @描述：发行帖子
 * @author HUANGYUAN
 * @TIME:2015年9月12日 上午12:00:07
 */
@Entity
@Table(name = "tribune_user_note")
public class UserNote extends BaseEntity {
	private Column column;// 帖子所属的模块
	private String noteContent;// 帖子的内容
	private String noteNumber;// 帖子编号
	private String noteTitle;// 帖子的标题
	private User user;// 写帖子的人,版主

	@ManyToOne
	@JoinColumn(name = "column_id")
	public Column getColumn() {
		return column;
	}

	@Lob
	public String getNoteContent() {
		return noteContent;
	}

	public String getNoteNumber() {
		return noteNumber;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteNumber(String noteNumber) {
		this.noteNumber = noteNumber;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
