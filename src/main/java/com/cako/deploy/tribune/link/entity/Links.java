package com.cako.deploy.tribune.link.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

/**
 * 关注
 * 
 * @author Davis
 *
 * @时间：2016年9月3日 上午2:23:15
 */
@Entity
@Table(name = "mc_links")
public class Links extends IdEntity {
	/**
	 * 关注的博客
	 */
	private UserNote userNote;

	private User user;// 关注人

	private User usered;// 被关注人

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

	@ManyToOne
	@JoinColumn(name = "usered_id")
	public User getUsered() {
		return usered;
	}

	public void setUsered(User usered) {
		this.usered = usered;
	}

}
