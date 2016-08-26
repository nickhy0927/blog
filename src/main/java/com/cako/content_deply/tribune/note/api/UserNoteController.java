package com.cako.content_deply.tribune.note.api;

import com.cako.content_deply.tribune.note.entity.CategoryTree;
import com.cako.content_deply.tribune.note.entity.UserNote;
import com.cako.content_deply.tribune.note.service.UserNoteService;
import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.config.Configuration;
import com.orm.enums.SysEnum.DeleteStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/outLink/tribune/user")
public class UserNoteController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserNoteService userNoteService;

    @RequestMapping(value = "/note/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("deleteStatus_eq", DeleteStatus.NO);
            List<Category> list = categoryService.queryByMap(paramMap);
            List<CategoryTree> columnTrees = new ArrayList<CategoryTree>();
            for (Category category : list) {
                columnTrees.add(new CategoryTree(category));
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
                Category category = categoryService.get(parentColumnId);
                userNote.setCategory(category);
                userNoteService.save(userNote);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }
}
