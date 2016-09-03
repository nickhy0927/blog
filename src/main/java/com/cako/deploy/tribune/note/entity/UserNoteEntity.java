package com.cako.deploy.tribune.note.entity;

import java.text.SimpleDateFormat;

public class UserNoteEntity {
	private String userId;
	private String loginname;
	private String filename;
	private String categoryName;
	private Integer count;
	private Integer zan = 0;

	private String id;// ID
	private Integer status;
	private String createTime;
	private String noteContent;// 帖子的内容
	private String noteNumber;// 帖子编号
	private String noteTitle;// 帖子的标题

	public UserNoteEntity(UserNote userNote, Integer count) {
		this.id = userNote.getId();
		this.status = userNote.getStatus();
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(userNote.getCreateTime());
		this.noteContent = userNote.getNoteContent();
		this.noteNumber = userNote.getNoteNumber();
		this.noteTitle = userNote.getNoteTitle();

		this.loginname = userNote.getUser().getLoginName();
		this.userId = userNote.getUser().getId();
		this.count = count;
		this.categoryName = userNote.getCategory().getName();
		this.filename = userNote.getUser().getAttachment().getName();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public UserNoteEntity() {

	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteNumber() {
		return noteNumber;
	}

	public void setNoteNumber(String noteNumber) {
		this.noteNumber = noteNumber;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
}
