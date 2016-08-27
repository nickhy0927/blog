package com.orm.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cako.init.InitUserInfo;
import com.cako.platform.menu.entity.Menu;
import com.cako.platform.role.entity.Role;
import com.cako.platform.user.entity.User;
import com.orm.login.SingleLogin;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private PageConfig pageConfig;

	@Autowired
	InitUserInfo initUserInfo;

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) { // 加入当前时间
			modelAndView.addObject("SESSIONID", request.getSession().getId().toUpperCase());
		}
	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		List<String> urls = pageConfig.getUrls();
		if (urls.contains(url)) {
			return true;
		}
		User user = SingleLogin.getUser(request.getSession());
		if (url.contains(pageConfig.getPathIgnore())) {
			return true;
		}
		if (user == null) {
			request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
			return false;
		} else {
			boolean access = isAccess(user, url);
			if (pageConfig.getUrls().contains(url)) {
				return true;
			}
			if (!access) {
				request.setAttribute("url", url);
				request.getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(request, response);
			}
			SingleLogin.isAlreadyEnter(request.getSession(), user.getLoginName());
			return true;
		}
	}

	private boolean isAccess(User user, String url) {
		Map<String, User> userMap = initUserInfo.getUserMap();
		user = userMap.get(user.getLoginName());
		if (user == null) {
			return false;
		}
		Set<Role> roles = user.getRoles();
		boolean flag = false;
		ok: for (Role role : roles) {
			Set<Menu> menus = role.getMenus();
			for (Menu menu : menus) {
				if (url.equals(menu.getHref())) {
					flag = true;
					break ok;
				}
			}
		}
		return flag;
	}
}
