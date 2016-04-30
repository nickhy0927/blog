package com.cako.ionic.common.utils;

import java.util.Map;

public class ResponseData {

	public int responseCode = CALLCODE.SUCCESS;
	public String responseMessage;
	public Map<String, Object> resultMap;

	public ResponseData() {
		if (this.responseCode == CALLCODE.SUCCESS) {
			setResponseMessage("获取数据成功.");
		} else {
			setResponseMessage("获取数据失败，请稍后再试.");
		}
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseCode(int responseCode, boolean flag) {
		this.responseCode = responseCode;
		if (flag) {
			setResponseMessage("获取数据成功.");
		} else {
			setResponseMessage("获取数据失败，请稍后再试.");
		}
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public static interface CALLCODE {
		public static int FAIL = 2;
		public static int SUCCESS = 1;
	}
}
