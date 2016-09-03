package com.cako.deploy.tribune.collect.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

/**
 * 收藏
 * 
 * @author Davis
 *
 * @时间：2016年9月3日 上午2:21:58
 */
@Entity
@Table(name = "mc_collect")
public class Collects extends IdEntity {

	private UserNote userNote;
	private User user;

	@ManyToOne
	@JoinColumn(name = "user_note_id")
	public UserNote getUserNote() {
		return userNote;
	}

	public void setUserNote(UserNote userNote) {
		this.userNote = userNote;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
