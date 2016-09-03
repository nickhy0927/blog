package com.cako.deploy.tribune.reply.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

/**
 * @描述：回复帖子
 * @author HUANGYUAN
 * @TIME:2015年9月11日 下午11:59:49
 */
@Entity
@Table(name = "t_user_reply")
public class ReplyNote extends IdEntity {

	private String message;
	private User user;
	private UserNote userNote;
	private ReplyNote replyNote;

	public String getMessage() {
		return message;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "p_id")
	public ReplyNote getReplyNote() {
		return replyNote;
	}

	public void setReplyNote(ReplyNote replyNote) {
		this.replyNote = replyNote;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
