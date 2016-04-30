package com.orm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orm.commons.spring.SpringContextHolder;

public class PageConfig {
	public static final Logger logger = LoggerFactory.getLogger(PageConfig.class);
	
	private String indexUrl;
	private String loginUrl;

	private static PageConfig config = null;

	public void init() {
		logger.info("正在初始化页面配置");
		if (config == null) {
			config = (PageConfig) SpringContextHolder.getBean("pageConfig");
		}
		logger.info("初始化页面配置成功");
		System.err.println(config);
	}

	public static PageConfig getPageConfig() {
		return config;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public String toString() {
		return "PageConfig [indexUrl=" + indexUrl + ", loginUrl=" + loginUrl + "]";
	}
}
