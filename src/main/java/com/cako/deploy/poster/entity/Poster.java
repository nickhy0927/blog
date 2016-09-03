package com.cako.deploy.poster.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.platform.attachment.entity.Attachment;
import com.orm.commons.utils.IdEntity;

@Entity
@Table(name = "mc_poster")
public class Poster extends IdEntity {

	private Attachment attachment;
	private Integer sort;// 排序

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
