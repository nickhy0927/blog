package com.cako.platform.category.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.cako.platform.utils.SysContants;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/admin/platform/category/create.html", method = RequestMethod.GET)
	public String create(Model model) {
		Category category = new Category();
		category.setCode(SysContants.CodeValue.getCodeValue("c"));
		model.addAttribute("category", category);
		return "admin/platform/category/create";
	}

	/**
	 * 保存
	 * 
	 * @param category
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/admin/platform/category/saveCategory.html", method = RequestMethod.POST)
	public void saveCategory(Category category, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			categoryService.save(category);
			map = SysContants.mapMsg.getMapMsg(true);
		} catch (Exception e) {
			e.printStackTrace();
			map = SysContants.mapMsg.getMapMsg(false);
		} finally {
			response.setHeader("ContentType", "text/html;charset=UTF-8");
			try {
				response.getWriter().write(new JsonMapper().toJson(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/admin/platform/category/categoryList.html", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String categoryList(HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ObjectTools<Category> tools = categoryService.queryPageByMap(map, currentPage,
					new Sort(Sort.Direction.DESC, "createTime"));
			model.addAttribute("categoryList", tools.getEntities());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pager", tools.getEntities().size() > 0 ? tools.getPager() : new Pager(0, "10", null));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/category/list";
	}
}
