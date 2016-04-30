package com.cako.ionic.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.support.json.JSONParser;

public class RequestData {

	public static Map<String, Object> getRequestDataToMap(HttpServletRequest request) {
		// 返回值Map
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			Entry<String, String[]> entry = entries.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			if (name.equals("data")) {
				JSONParser parser = new JSONParser(value);
				Map<String, Object> parseMap = parser.parseMap();
				Set<String> keySet = parseMap.keySet();
				for (String key : keySet) {
					paramsMap.put(key, parseMap.get(key));
				}
			} else {
				paramsMap.put(name, value);
			}
		}
		return paramsMap;
	}
}
