package com.cako.content_deply.tribune.note.entity;

import java.util.List;

import com.cako.content_deply.tribune.reply.entity.ReplyNote;

public class UserNoteDto {
	private UserNote userNote;
	private Integer count;
	private Integer zan = 0;
	private List<ReplyNote> replyNotes;

	public UserNoteDto(UserNote userNote, Integer count) {
		this.userNote = userNote;
		this.count = count;
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
		this.userNote = userNote;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
