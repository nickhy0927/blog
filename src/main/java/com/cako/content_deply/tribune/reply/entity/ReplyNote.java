package com.cako.content_deply.tribune.reply.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.basic.tribune.note.entity.UserNote;
import com.cako.platform.user.entity.User;
import com.cako.platform.utils.BaseEntity;

/**
 * @描述：回复帖子
 * @author HUANGYUAN
 * @TIME:2015年9月11日 下午11:59:49
 */
@Entity
@Table(name = "t_user_reply")
public class ReplyNote extends BaseEntity {

	private String message;
	private User user;
	private UserNote userNote;

	public String getMessage() {
		return message;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	@ManyToOne
	@JoinColumn(name = "user_note_id")
	public UserNote getUserNote() {
		return userNote;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserNote(UserNote userNote) {
		this.userNote = userNote;
	}

}
