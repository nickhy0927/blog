package com.cako.platform.menu.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.menu.entity.Menu;
import com.cako.platform.menu.service.MenuService;
import com.cako.platform.menu.tree.MenuTree;
import com.cako.platform.utils.BaseController;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;

/**
 * Created by Curtain on 2015/9/15.
 */
@Controller
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/admin/platform/menu/create.html")
	public String create(Model model) {
		try {
			List<Menu> list = menuService.findAll();
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : list) {
				menuTrees.add(new MenuTree(menu));
			}
			model.addAttribute("menuTrees", new JsonMapper().toJson(menuTrees));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/module/create";
	}

	@RequestMapping(value = "/admin/platform/menu/edit.html")
	public String edit(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			if (StringUtils.isNotEmpty(id)) {
				Menu menu = menuService.get(id);
				model.addAttribute("menu", menu);
			}
			List<Menu> list = menuService.findAll();
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : list) {
				menuTrees.add(new MenuTree(menu));
			}
			model.addAttribute("menuTrees", new JsonMapper().toJson(menuTrees));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/module/create";
	}

	@RequestMapping(value = "/admin/platform/menu/list.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String menuList(HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ObjectTools<Menu> tools = menuService.queryPageByMap(map, currentPage,
					new Sort(Sort.Direction.DESC, "createTime"));
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : tools.getEntities()) {
				menuTrees.add(new MenuTree(menu));
			}
			model.addAttribute("pager", tools.getEntities().size() > 0 ? tools.getPager() : new Pager(0, "10", null));
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("menuTrees", menuTrees);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/module/list";
	}

	@RequestMapping(value = "/admin/platform/menu/save.html", method = RequestMethod.POST)
	public void menuSave(HttpServletRequest request, Menu menu, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String parentId = request.getParameter("parentId");
			if (StringUtils.isNotEmpty(parentId)) {
				menu.setMenu(menuService.get(parentId));
			}
			menuService.save(menu);
			map.put("status", "y");
			map.put("info", "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "新增失败");
			map.put("status", "n");
		} finally {
			response.setHeader("ContentType", "text/html;charset=UTF-8");
			try {
				response.getWriter().write(new JsonMapper().toJson(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
