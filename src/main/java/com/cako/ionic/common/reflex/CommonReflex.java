package com.cako.ionic.common.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cako.ionic.common.utils.ParseAccessXml;
import com.cako.ionic.common.utils.RequestData;
import com.cako.ionic.common.utils.ResponseData.CALLCODE;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orm.commons.spring.SpringContextHolder;
import com.orm.commons.utils.JsonMapper;

@Service
public class CommonReflex {

	public String getObject(HttpServletRequest request) throws JsonMappingException {
		Map<String, Object> requestDataToMap = RequestData.getRequestDataToMap(request);
		System.out.println(requestDataToMap.toString());
		Map<String, Object> map = ParseAccessXml.map;
		String serviceCode = requestDataToMap.get("serviceCode").toString();
		String serviceName = map.get(serviceCode).toString();
		Object object = SpringContextHolder.getBean(serviceName);
		String className = object.getClass().getName();
		String callbackFunName = requestDataToMap.get("callback").toString();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(callbackFunName);
		Class<?> classType = null;
		try {
			classType = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String methodCode = requestDataToMap.get("methodCode").toString();
		String methodName = map.get(methodCode).toString();
		// 获取Service类的方法
		Object invokeObject;
		Object result = null;
		try {
			requestDataToMap.remove("methodCode");
			requestDataToMap.remove("serviceCode");
			requestDataToMap.remove("callback");
			invokeObject = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			Method method = classType.getMethod(methodName, new Class[] { Map.class });
			result = method.invoke(invokeObject, new Object[] { requestDataToMap });
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		stringBuilder.append("(");
		if (result == null) {
			map = new HashMap<String, Object>();
			map.put("responseCode", CALLCODE.FAIL);
			map.put("responseMessage", "操作失败，请稍后再试");
			result = new JsonMapper().toJson(map);
		}
		stringBuilder.append(new JsonMapper().toJson(result));
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
