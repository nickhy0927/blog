package com.cako.ionic.service.tribune;

import java.util.List;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.platform.category.entity.Category;

public class CategoryTribune {

	private Category category;
	private List<UserNote> userNotes;

	public CategoryTribune(Category category, List<UserNote> userNotes) {
		this.category = category;
		this.userNotes = userNotes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<UserNote> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(List<UserNote> userNotes) {
		this.userNotes = userNotes;
	}
}
