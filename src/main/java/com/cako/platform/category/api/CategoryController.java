package com.cako.platform.category.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.cako.platform.utils.SysContants;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.WebUtils;

@RequestMapping(value = "admin/platform")
public class CategoryController implements Controller{

	@Autowired
	private CategoryService categoryService;
	
	public String create(Model model) {
		Category category = new Category();
		category.setCode(SysContants.CodeValue.getCodeValue("c"));
		model.addAttribute("category", category);
		return "admin/platform/category/create";
	}
	
	/**
	 * 保存
	 * @param category
	 * @param request
	 * @param response
	 */
	public void save(Category category,HttpServletRequest request,HttpServletResponse response) {
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
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramsToMap = WebUtils.getParamsToMap(request);
		request.setAttribute("paramsMap", paramsToMap);
		return new ModelAndView("");
	}

}
