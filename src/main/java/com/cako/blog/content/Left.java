package com.cako.blog.content;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class Left extends TagSupport{
	
	@Override
	public int doEndTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		System.out.println(object);
		return SKIP_PAGE;
	}
}
