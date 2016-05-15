package com.cako.blog.content;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("serial")
public class Header extends TagSupport {

	private Map<String, Object> dataModel = new HashMap<String, Object>();
	private String id  = "1";
	
	public Map<String, Object> getDataModel() {
		return dataModel;
	}
	
	public void setDataModel(Map<String, Object> dataModel) {
		this.dataModel = dataModel;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}
	
	@Override
	public int doEndTag() throws JspException {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "template");
		try {
			Template template = cfg.getTemplate("header.ftl", "UTF-8");
			StringWriter writer = new StringWriter();
			dataModel.put("name", "张三");
			dataModel.put("ctx", pageContext.getServletContext().getContextPath());
			template.process(dataModel, writer);
			this.pageContext.getOut().write(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
