package com.orm.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.BeansException;

public class ShiroFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
//			InitEnvironment environment = InitEnvironment.getInitEnvironmentInstance();
//			HttpServletRequest httpRequest = (HttpServletRequest) request;
//			HttpServletResponse httpResponse = (HttpServletResponse) response;
//			HttpSession session = httpRequest.getSession(true);
//			String url = httpRequest.getRequestURL().toString();
//			if (url.contains(environment.getIgnoreResources()) || url.contains(environment.getOutsideOfficeHoursPage())) {
//				chain.doFilter(request, response);
//			} else {
//				Object object = session.getAttribute("user");
//				if (object != null) {
//					chain.doFilter(request, response);
//				} else {
//					httpResponse.sendRedirect(environment.getOutsideOfficeHoursPage());
//				}
//			}

			chain.doFilter(request, response);
		} catch (BeansException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
