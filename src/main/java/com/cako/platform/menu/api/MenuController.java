package com.cako.platform.menu.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.ObjectTools;

/**
 * Created by Curtain on 2015/9/15.
 */
@Controller
@RequestMapping(value = "/platform")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
    @RequestMapping(value = "menu/menuCreate")
    public String menuCreate(HttpServletRequest request,Model model){
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
        return "platform/menu/menuCreate";
    }
    
    @RequestMapping(value = "menu/menuSave",method = RequestMethod.POST)
    public String menuSave(HttpServletRequest request,Menu menu,Model model){
    	try {
    		String parentId = request.getParameter("parentId");
    		if (StringUtils.isNotEmpty(parentId)) {
    			menu.setMenu(menuService.get(parentId));
			}
			menuService.save(menu);
    	} catch (ServiceException e) {
			e.printStackTrace();
		}
    	return "redirect:menuList";
    }
    
    @RequestMapping(value = "menu/menuList",method = {RequestMethod.POST,RequestMethod.GET})
    public String menuList(HttpServletRequest request,Model model){
    	String currentPage = request.getParameter("currentPage");
    	Map<String, Object> map = new HashMap<String,Object>();
		try {
			ObjectTools<Menu> tools = menuService.queryPageByMap(map , currentPage, new Sort(Sort.Direction.DESC,"createTime"));
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : tools.getEntities()) {
				menuTrees.add(new MenuTree(menu));
			}
			model.addAttribute("tools", tools);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("menuTrees", menuTrees);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	return "platform/menu/menuList";
    }

}
