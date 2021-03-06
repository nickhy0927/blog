package com.cako.deploy.tribune.note.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.platform.category.entity.Category;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

/**
 * @描述：发行帖子
 * @author HUANGYUAN
 * @TIME:2015年9月12日 上午12:00:07
 */
@Entity
@Table(name = "t_user_note")
public class UserNote extends IdEntity {
	private Category category;// 帖子所属的模块
	private String noteContent;// 帖子的内容
	private String noteNumber;// 帖子编号
	private String noteTitle;// 帖子的标题
	private String isShow;
	private User user;// 写帖子的人,版主

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
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

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}
