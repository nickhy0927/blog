package com.cako.content_deply.tribune.note.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cako.platform.role.entity.Role;
import com.orm.commons.utils.MessageObject;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;
import com.orm.commons.utils.WebUtils;
import com.orm.enums.SysEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    private MessageObject message = new MessageObject();

    @RequestMapping(value = "/note/create.html", method = RequestMethod.GET)
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

    @RequestMapping(value = "/note/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        Map<String, Object> paramMap = WebUtils.getParamsToMap(request);
        String currentPage = request.getParameter("currentPage");
        paramMap.put("status_eq", SysContants.RecordType.VALID);
        try {
            ObjectTools<UserNote> tools = userNoteService.queryPageByMap(paramMap, currentPage, new Sort(Sort.Direction.DESC, "createTime"));
            model.addAttribute("notes", tools.getEntities());
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("pager", tools.getEntities().size() > 0 ? tools.getPager() : new Pager(0, "10"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "tribune/userNote/list";
    }

    /**
     * 删除
     * @param id
     * @param response
     */
    @RequestMapping(value = "/note/delete/{id}.html", method = RequestMethod.GET)
    public void noteDelete(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(id)) {
                UserNote userNote = userNoteService.get(id);
                userNote.setStatus(SysContants.RecordType.VALID);
                userNoteService.save(userNote);
            } else {
                message.setErrorMessage("帖子删除失败，请稍后再试");
            }
        } catch (ServiceException e) {
            message.setErrorMessage("帖子删除失败，请稍后再试");
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().write(message.getJsonMapper(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/note/edit/{id}.html", method = RequestMethod.GET)
    public String roleEdit(@PathVariable("id") String id, Model model) {
        try {
            if (StringUtils.isNotEmpty(id)) {
                UserNote note = userNoteService.get(id);
                model.addAttribute("note", note);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "tribune/userNote/view";
    }
}
