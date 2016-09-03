package com.cako.deploy.tribune.note.entity;

import java.util.List;

import com.cako.deploy.tribune.reply.entity.ReplyNote;

public class UserNoteDto {
	private UserNote userNote;
	private String userId;
	private String loginname;
	private String filename;
	private String categoryName;
	private Integer count;
	private Integer zan = 0;
	private List<ReplyNote> replyNotes;

	public UserNoteDto(UserNote userNote, Integer count) {
		this.userNote = userNote;
		this.loginname = userNote.getUser().getLoginName();
		this.userId = userNote.getUser().getId();
		this.count = count;
		this.filename = userNote.getUser().getAttachment().getName();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ReplyNote> getReplyNotes() {
		return replyNotes;
	}

	public void setReplyNotes(List<ReplyNote> replyNotes) {
		this.replyNotes = replyNotes;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public UserNoteDto() {

	}

	public UserNote getUserNote() {
		return userNote;
	}

	public void setUserNote(UserNote userNote) {
		userNote.setUser(null);
		userNote.setCategory(null);
		this.userNote = userNote;
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
}
