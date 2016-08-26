package com.cako.platform.utils;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Curtain on 2015/8/7.
 */
public class BeanToMapUtil {
	public static final String setMethodModify = "set";

	public static Object getBeenObjectByRequest(Object object, HttpServletRequest request) {
		Map<String, Object> map = getRequestToMap(request);
		object = getObjectBeenByRequert(object, map);
		return object;
	}

	private static Map<String, Object> getRequestToMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					paramName = paramName.toUpperCase();
					map.put(paramName, paramValue);
				}
			}
		}
		return map;
	}

	public static Object getObjectBeenByRequert(Object obj, Map<String, Object> map) {
		Class<?> class1 = obj.getClass();
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith(BeanToMapUtil.setMethodModify)) {
				String propertyName = methodName.substring(3).toUpperCase(Locale.getDefault());
				String value = map.get(propertyName) != null ? map.get(propertyName).toString() : "";
				System.out.println(propertyName.toLowerCase() + " = " + value);
				try {
					method.invoke(obj, value);
				} catch (Exception e) {
				}
			}
		}
		System.out.println(obj);
		return obj;
	}
}
