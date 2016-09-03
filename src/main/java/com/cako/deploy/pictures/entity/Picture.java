package com.cako.deploy.pictures.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.user.entity.User;
import com.orm.commons.utils.IdEntity;

/**
 * 相册
 * 
 * @author Davis
 *
 * @时间：2016年9月3日 上午2:38:36
 */
@Entity
@Table(name = "mc_picture")
public class Picture extends IdEntity {

	private User user;
	private Attachment attachment;// 图片

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "attach_id")
	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
}
