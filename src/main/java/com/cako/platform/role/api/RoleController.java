package com.cako.platform.role.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.cako.platform.role.entity.Role;
import com.cako.platform.role.service.RoleService;
import com.cako.platform.utils.BaseController;
import com.cako.platform.utils.SysContants;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;
import com.orm.enums.SysEnum.DeleteStatus;

/**
 * Created by Curtain on 2015/9/21.
 */
@Controller
public class RoleController extends BaseController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	/**
	 * 获取菜单列表 Davis
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/admin/platform/role/addMenuToRole.html")
	public void addMenuToRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String menuIdsStr = request.getParameter("menuIds");
			String roleId = request.getParameter("roleId");
			if (StringUtils.isNotEmpty(roleId)) {
				Role role = roleService.get(roleId);
				String[] menuIds = menuIdsStr.split(",");
				role.setStatus(SysContants.RecordType.VALID);
				Set<Menu> menus = new HashSet<Menu>();
				for (String menuId : menuIds) {
					Menu menu = menuService.get(menuId);
					if (menu != null) {
						menus.add(menu);
					}
				}
				role.setMenus(menus);
				roleService.save(role);
				message.setInforMessage("角色赋权成功");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			message.setErrorMessage("角色赋权失败");
		} finally {
			try {
				response.getWriter().write(new JsonMapper().toJson(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/admin/platform/role/create.html", method = RequestMethod.GET)
	public String roleCreate(HttpServletRequest request, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		model.addAttribute("code", sdf.format(new Date()));
		return "admin/platform/role/create";
	}

	/**
	 * @描述：删除角色
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:58:31
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/admin/platform/role/delete.html", method = RequestMethod.GET)
	public void roleDelete(HttpServletResponse response, HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			if (StringUtils.isNotEmpty(id)) {
				Role role = roleService.get(id);
				if (role != null) {
					DeleteStatus deleteStatus = DeleteStatus.YES;
					role.setDeleteStatus(deleteStatus);
					roleService.save(role);
					message.setInforMessage("角色删除成功");
				} else {
					message.setErrorMessage("角色删除失败，请稍后再试");
				}
			} else {
				message.setErrorMessage("角色删除失败，请稍后再试");
			}
		} catch (ServiceException e) {
			message.setErrorMessage("角色删除失败，请稍后再试");
			e.printStackTrace();
		} finally {
			try {
				response.getWriter().write(message.getJsonMapper(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @描述：修改角色
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:58:45
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/platform/role/edit.html", method = RequestMethod.GET)
	public String roleEdit(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		try {
			if (StringUtils.isNotEmpty(id)) {
				Role role = roleService.get(id);
				model.addAttribute("role", role);
				model.addAttribute("code", role.getCode());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/role/create";
	}

	/**
	 * @描述：角色列表
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:58:59
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/platform/role/list.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String roleList(HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteStatus_eq", DeleteStatus.NO);
		try {
			List<Menu> list = menuService.findAll();
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : list) {
				menuTrees.add(new MenuTree(menu));
			}
			model.addAttribute("menuTrees", new JsonMapper().toJson(menuTrees));
			ObjectTools<Role> tools = roleService.queryPageByMap(map, currentPage,
					new Sort(Sort.Direction.DESC, "createTime"));
			model.addAttribute("rolesList", tools.getEntities());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pager", tools.getEntities().size() > 0 ? tools.getPager() : new Pager(0, "10", null));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/role/list";
	}

	/**
	 * @描述：保存角色
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:59:21
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/admin/platform/role/save", method = RequestMethod.POST)
	public void roleSave(HttpServletResponse response, Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			roleService.save(role);
			map.put("info", "新增成功");
			map.put("status", "y");
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
