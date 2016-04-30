package com.cako.basic.tribune.note.api;

import java.util.ArrayList;
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

import com.cako.basic.topic.column.entity.Column;
import com.cako.basic.topic.column.service.ColumnService;
import com.cako.basic.topic.column.tree.ColumnTree;
import com.cako.basic.tribune.note.entity.UserNote;
import com.cako.basic.tribune.note.service.UserNoteService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.config.Configuration;
import com.orm.enums.SysEnum.DeleteStatus;

@Controller
@RequestMapping(value = "/outLink/tribune/user")
public class UserNoteController {

	@Autowired
	private ColumnService columnService;

	@Autowired
	private UserNoteService userNoteService;

	@RequestMapping(value = "/note/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			paramMap.put("deleteStatus_eq", DeleteStatus.NO);
			List<Column> list = columnService.queryByMap(paramMap);
			List<ColumnTree> columnTrees = new ArrayList<ColumnTree>();
			for (Column column : list) {
				columnTrees.add(new ColumnTree(column));
			}
			model.addAttribute("columnTrees", new JsonMapper().toJson(columnTrees));
			model.addAttribute("noteNumber", Configuration.getModuleNumber("NOTE", true, 0));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "tribune/userNote/userNoteCreate";
	}

	@RequestMapping(value = "/note/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, UserNote userNote) {
		try {
			String parentColumnId = request.getParameter("parentColumnId");
			if (StringUtils.isNotEmpty(parentColumnId)) {
				Column column = columnService.get(parentColumnId);
				userNote.setColumn(column);
				userNoteService.save(userNote);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "redirect:list";
	}

}
