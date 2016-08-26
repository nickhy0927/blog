package com.orm.commons.utils;

import org.apache.commons.lang3.StringUtils;

public class MessageObject {

	public static interface ResponseCode {
		public static final Integer code_100 = 100;
		/**
		 * 请求成功代码
		 */
		public static final Integer code_200 = 200;
		/**
		 * 请求错误代码
		 */
		public static final Integer code_403 = 403;

		/**
		 * 请求失败代码
		 */
		public static final Integer code_404 = 404;
	}

	private String message;

	private Object object;
	private Integer resposecode;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getJsonMapper(MessageObject message) {
		if (message != null) {
			return new JsonMapper().toJson(message);
		}
		return "";
	}

	public String getMessage() {
		return message;
	}

	public Integer getResposecode() {
		return resposecode;
	}

	public void setErrorMessage(String errorMessage) {
		this.message = errorMessage;
		this.resposecode = ResponseCode.code_404;
	}

	public void setInforMessage(String inforMessage) {
		this.message = inforMessage;
		this.resposecode = ResponseCode.code_200;
		if (StringUtils.isEmpty(inforMessage)){
			this.resposecode = null;
		}

	}

	public void setResposecode(Integer resposecode) {
		this.resposecode = resposecode;
	}
}
