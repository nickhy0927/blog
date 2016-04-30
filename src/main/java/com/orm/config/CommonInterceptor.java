package com.orm.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cako.platform.user.entity.User;
import com.orm.login.SingleLogin;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		InitEnvironment environment = InitEnvironment.getInitEnvironmentInstance();
		if (url.contains(environment.getIgnoreResources()) || url.contains("/login")
				|| url.contains(environment.getIgnoreOutLinkAddress())) {
			return true;
		}
		if (SingleLogin.isOnline(request.getSession())) {
			return true;
		}
		User user = SingleLogin.getUser(request.getSession());
		if (user == null) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return false;
		} else
			return true;
	}
}
