package com.orm.config;

import com.orm.commons.spring.SpringContextHolder;

public class InitEnvironment {
	private static InitEnvironment environment = null;
	private static InitEnvironment getInitEnvironment() {
		return environment;
	}

	public static InitEnvironment getInitEnvironmentInstance() {
		return getInitEnvironment();
	}

	private String cmsIndex;// 前台首页
	private String errorPage;// 前台错误页面
	private String ignoreOutLinkAddress;// 外部链接
	private String ignoreResources;// 需要忽略的文件或者路径
	private String initPassword;

	private String initUsername;

	private String outsideOfficeHoursPage;// outsideOfficeHoursPage属性指定错误提示页面的URL

	public String getCmsIndex() {
		return cmsIndex;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public String getIgnoreOutLinkAddress() {
		return ignoreOutLinkAddress;
	}

	public String getIgnoreResources() {
		return ignoreResources;
	}

	public String getInitPassword() {
		return initPassword;
	}

	public String getInitUsername() {
		return initUsername;
	}

	public String getOutsideOfficeHoursPage() {
		return outsideOfficeHoursPage;
	}

	public void init() {
		if (environment == null) {
			environment = SpringContextHolder.getBean(InitEnvironment.class);
		}
	}

	public void setCmsIndex(String cmsIndex) {
		this.cmsIndex = cmsIndex;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public void setIgnoreOutLinkAddress(String ignoreOutLinkAddress) {
		this.ignoreOutLinkAddress = ignoreOutLinkAddress;
	}

	public void setIgnoreResources(String ignoreResources) {
		this.ignoreResources = ignoreResources;
	}

	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}

	public void setInitUsername(String initUsername) {
		this.initUsername = initUsername;
	}

	public void setOutsideOfficeHoursPage(String outsideOfficeHoursPage) {
		this.outsideOfficeHoursPage = outsideOfficeHoursPage;
	}

	@Override
	public String toString() {
		return "InitEnvironment [outsideOfficeHoursPage=" + outsideOfficeHoursPage + ", ignoreResources=" + ignoreResources
				+ ", errorPage=" + errorPage + ", cmsIndex=" + cmsIndex + ", initUsername=" + initUsername + ", initPassword="
				+ initPassword + "]";
	}

}
