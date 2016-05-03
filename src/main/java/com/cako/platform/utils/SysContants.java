package com.cako.platform.utils;

import java.util.HashMap;
import java.util.Map;

public class SysContants {

	public static class mapMsg {
		public static Map<String, Object> getMapMsg(boolean flag) {
			Map<String, Object> map = new HashMap<String,Object>();
			if (flag) {
				map.put("status", "y");
				map.put("info", "新增成功");
			} else {
				map.put("info", "新增失败");
				map.put("status", "n");
			}
			return map;
		}
	}
	
	public static class RecordType {
		public static int VALID = 1;// 有效
		public static int UNVALID = 2;// 无效
		
	}
}
