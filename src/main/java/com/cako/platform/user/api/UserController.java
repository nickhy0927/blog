package com.cako.platform.user.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.role.entity.Role;
import com.cako.platform.role.service.RoleService;
import com.cako.platform.role.tree.RoleTree;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.cako.platform.user.tree.UserClazz;
import com.cako.platform.utils.BaseController;
import com.cako.platform.utils.BeanToMapUtil;
import com.orm.commons.encryption.MD5Encryption;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.MessageObject;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;
import com.orm.enums.SysEnum;
import com.orm.enums.SysEnum.DeleteStatus;
import com.orm.login.SingleLogin;

@Controller
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/platform/user/addRole.html", method = RequestMethod.GET)
	public void addRole(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			String roleIds = request.getParameter("roleIds");
			Set<Role> roles = new HashSet<Role>();
			if (StringUtils.isNotEmpty(roleIds)) {
				String[] ids = roleIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					Role role = roleService.get(ids[i]);
					if (role != null) {
						roles.add(role);
					}
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				User user = userService.get(id);
				if (user != null) {
					user.setRoles(roles);
					userService.save(user);
					message.setInforMessage("添加角色成功");
				} else {
					message.setInforMessage("添加角色失败");
				}
			} else {
				message.setInforMessage("添加角色失败");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			message.setInforMessage("添加角色失败");
		} finally {
			try {
				response.getWriter().write(message.getJsonMapper(message));
			} catch (IOException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/admin/platform/user/delete.html")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		MessageObject message = new MessageObject();
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			try {
				User user = userService.get(id);
				DeleteStatus deleteStatus = DeleteStatus.YES;
				user.setDeleteStatus(deleteStatus);
				userService.save(user);
				message.setInforMessage("删除成功");
			} catch (ServiceException e) {
				e.printStackTrace();
				message.setErrorMessage("删除失败，请稍后再试");
			} finally {
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(message.getJsonMapper(message));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/admin/platform/user/edit.html")
	public String edit(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			try {
				User user = userService.get(id);
				model.addAttribute("user", user);
				model.addAttribute("userType", user.getUserType());
				List<Map<String, Object>> userTypeList = SysEnum.getUserType();
				model.addAttribute("userTypeList", userTypeList);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return "admin/platform/user/create";
	}

	/**
	 * 获取角色信息 Davis
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/platform/user/getRoleList.html")
	public void getRoleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			List<Role> roles = roleService.queryByMap(paramMap);
			List<RoleTree> roleTrees = new ArrayList<RoleTree>();
			for (Role role : roles) {
				roleTrees.add(new RoleTree(role));
			}
			User user = SingleLogin.getUser(request.getSession());
			List<String> roleIds = new ArrayList<String>();
			Set<Role> roleList = user.getRoles();
			Map<String, Object> map = new HashMap<String, Object>();
			for (Iterator<Role> iterator = roleList.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				roleIds.add(role.getId());
			}
			map.put("roleTrees", roleTrees);
			map.put("roleIds", roleIds);
			message.setObject(map);
			message.setInforMessage("获取角色信息成功");
		} catch (ServiceException e) {
			e.printStackTrace();
			message.setErrorMessage("获取角色信息失败");
		} finally {
			response.getWriter().write(new JsonMapper().toJson(message));
		}
	}

	@RequestMapping(value = "/admin/platform/user/list.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String currentPage = request.getParameter("currentPage");
		paramMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
		try {
			ObjectTools<User> users = userService.queryPageByMap(paramMap, currentPage,
					new Sort(Sort.Direction.DESC, "createTime"));
			List<UserClazz> userClazzs = new ArrayList<UserClazz>();
			for (User user : users.getEntities()) {
				userClazzs.add(new UserClazz(user));
			}
			model.addAttribute("list", userClazzs);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pager", users.getEntities().size() > 0 ? users.getPager() : new Pager(0, "10"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "admin/platform/user/list";
	}

	@RequestMapping(value = "/admin/platform/user/save.html", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String userTypes = request.getParameter("userTypes");
			User user = (User) BeanToMapUtil.getBeenObjectByRequest(new User(), request);
			user.setPassword(MD5Encryption.MD5(user.getPassword()));
			user.setUserType(SysEnum.getUserTypeByName(userTypes));
			user = userService.save(user);
			System.out.println(user);
			map.put("info", "新增成功");
			map.put("status", "y");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("info", "新增失败");
			map.put("status", "n");
		} finally {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(new JsonMapper().toJson(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/admin/platform/user/create.html")
	public String userCreate(HttpServletRequest request, Model model) {
		List<Map<String, Object>> userTypeList = SysEnum.getUserType();
		request.setAttribute("userTypeList", userTypeList);
		return "admin/platform/user/create";
	}

	@RequestMapping(value = "/admin/platform/user/validLoginName.json", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void validLoginName(HttpServletRequest request, HttpServletResponse response) {
		String loginName = request.getParameter("param");
		String id = request.getParameter("id");
		System.out.println(id);
		User user = userService.findUserByLoginName(loginName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("info", "该用户可以使用.");
			map.put("status", "y");
		} else {
			map.put("info", "该用户已存在，请更换其他的账号注册.");
			map.put("status", "n");
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(new JsonMapper().toJson(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
