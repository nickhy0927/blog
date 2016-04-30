package com.cako.platform.role.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.platform.role.entity.Role;
import com.cako.platform.role.service.RoleService;
import com.cako.platform.utils.BaseController;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.ObjectTools;
import com.orm.enums.SysEnum.DeleteStatus;

/**
 * Created by Curtain on 2015/9/21.
 */
@Controller
@RequestMapping(value = "/platform")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/role/roleCreate", method = RequestMethod.GET)
	public String roleCreate(HttpServletRequest request, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		model.addAttribute("code", sdf.format(new Date()));
		return "/platform/role/roleCreate";
	}

	/**
	 * @描述：删除角色
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:58:31
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/role/roleDelete/{id}", method = RequestMethod.GET)
	public void roleDelete(@PathVariable("id") String id, HttpServletResponse response) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Role role = roleService.get(id);
				if (role != null) {
					DeleteStatus deleteStatus = DeleteStatus.YES;
					role.setDeleteStatus(deleteStatus);
					roleService.save(role);
					message.setInforMessage("角色删除成功");
				} else {
					message.setErrorMessage("角色删除失败");
				}
			} else {
				message.setErrorMessage("角色删除失败");
			}
		} catch (ServiceException e) {
			message.setErrorMessage("角色删除失败");
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
	@RequestMapping(value = "/role/roleEdit/{id}", method = RequestMethod.GET)
	public String roleEdit(@PathVariable("id") String id, Model model) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Role role = roleService.get(id);
				model.addAttribute("role", role);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "/platform/role/roleUpdate";
	}

	/**
	 * @描述：角色列表
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:58:59
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/role/roleList", method = { RequestMethod.POST, RequestMethod.GET })
	public String roleList(HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteStatus_eq", DeleteStatus.NO);
		try {
			ObjectTools<Role> tools = roleService.queryPageByMap(map, currentPage, new Sort(Sort.Direction.DESC, "createTime"));
			model.addAttribute("rolesList", tools.getEntities());
			model.addAttribute("tools", tools);
			model.addAttribute("currentPage", currentPage);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "platform/role/roleList";
	}

	/**
	 * @描述：保存角色
	 * @author HUANGYUAN
	 * @TIME:2015年9月21日 下午10:59:21
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/role/roleSave", method = RequestMethod.POST)
	public String roleSave(HttpServletRequest request, Model model, Role role) {
		try {
			roleService.save(role);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "redirect:roleList";
	}
}
