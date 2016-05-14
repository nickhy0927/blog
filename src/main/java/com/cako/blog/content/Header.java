package com.cako.blog.content;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class Header extends TagSupport{

	@Override
	public int doEndTag() throws JspException {
		return SKIP_PAGE;
	}
}
