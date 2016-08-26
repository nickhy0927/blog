package com.orm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orm.commons.spring.SpringContextHolder;

public class PageConfig {
	public static final Logger logger = LoggerFactory.getLogger(PageConfig.class);
	
	private String indexUrl;
	private String loginUrl;
	private String initUserName;
	private String initPassword;
	private String pathIgnore;

	private static PageConfig config = null;

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

	public String getInitPassword() {
		return initPassword;
	}

	public String getInitUserName() {
		return initUserName;
	}

	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}

	public void setInitUserName(String initUserName) {
		this.initUserName = initUserName;
	}

	public String getPathIgnore() {
		return pathIgnore;
	}

	public void setPathIgnore(String pathIgnore) {
		this.pathIgnore = pathIgnore;
	}
}
