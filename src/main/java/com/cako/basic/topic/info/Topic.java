package com.cako.basic.topic.info;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.basic.topic.column.entity.Column;
import com.cako.platform.user.entity.User;
import com.cako.platform.utils.BaseEntity;

/**
 * @描述：栏目资讯
 * @author HUANGYUAN
 * @TIME:2015年9月13日 下午1:31:49
 */
@Entity
@Table(name = "t_topic_info")
public class Topic extends BaseEntity {

	private Column column;
	private String content;
	private String title;
	private User user;

	@ManyToOne
	@JoinColumn(name = "column_id")
	public Column getColumn() {
		return column;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
