package com.cako.content_deply.tribune.note.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.content_deply.tribune.note.entity.UserNote;
import com.cako.content_deply.tribune.note.service.UserNoteService;
import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.cako.platform.user.entity.User;
import com.cako.platform.utils.SysContants;
import com.orm.commons.exception.ServiceException;
import com.orm.config.Configuration;
import com.orm.login.SingleLogin;

@Controller
@RequestMapping(value = "/tribune/user")
public class UserNoteController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserNoteService userNoteService;

	@RequestMapping(value = "/note/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			List<Category> list = categoryService.queryByMap(paramMap);
			model.addAttribute("list", list);
			model.addAttribute("noteNumber", Configuration.getModuleNumber("NOTE", true, 0));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "tribune/userNote/userNoteCreate";
	}

	@RequestMapping(value = "/note/save.html", method = RequestMethod.POST)
	public void save(HttpServletRequest request, UserNote userNote) {
		try {
			String categoryId = request.getParameter("categoryId");
			User user = SingleLogin.getUser(request.getSession());
			if (StringUtils.isNotEmpty(categoryId)) {
				Category category = categoryService.get(categoryId);
				userNote.setCategory(category);
				userNote.setStatus(SysContants.RecordType.VALID);
				userNote.setUser(user);
				userNoteService.save(userNote);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
