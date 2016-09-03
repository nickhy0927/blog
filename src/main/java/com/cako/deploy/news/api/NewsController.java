package com.cako.deploy.news.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.utils.BaseController;

/**
 * 新闻
 * 
 * @author HUANGYUAN
 *
 */
@Controller
@Scope("prototype")
public class NewsController extends BaseController {

	@RequestMapping(value = "/content/deploy/news/create.html")
	public void create(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 新增 Davis
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/content/deploy/news/save.html", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 删除 Davis
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/content/deploy/news/delete.html")
	public void delete(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 修改 Davis
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/content/deploy/news/edit.html")
	public void edit(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 查询 Davis
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/content/deploy/news/list.html")
	public void list(HttpServletRequest request, HttpServletResponse response) {

	}

}
