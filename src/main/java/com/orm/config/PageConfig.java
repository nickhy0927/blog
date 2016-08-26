package com.orm.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageConfig {
	public static final Logger logger = LoggerFactory.getLogger(PageConfig.class);

	private String indexUrl;
	private String loginUrl;
	private String initUserName;
	private String initPassword;
	private String pathIgnore;
	private List<String> urls;

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

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
}
