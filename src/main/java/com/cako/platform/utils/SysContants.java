package com.cako.platform.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SysContants {

	/**
	 * 栏目类型
	 * 
	 * @author HUANGYUAN
	 *
	 */
	public interface CategoryType {
		public final static Integer COLUMN_1 = 1;// 博客类型
		public final static Integer COLUMN_2 = 2;// 新闻类型
	}

	public static class CodeValue {
		public static String getCodeValue(String prefux) {
			String code = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			if (StringUtils.isNotEmpty(prefux)) {
				code = prefux + sdf.format(new Date());
			} else {
				code = sdf.format(new Date());
			}
			return code;
		}
	}

	public static class mapMsg {
		public static Map<String, Object> getMapMsg(boolean flag) {
			Map<String, Object> map = new HashMap<String, Object>();
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
