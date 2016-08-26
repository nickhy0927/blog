package com.orm.commons.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageSupport extends PageRequest {
	private static final long serialVersionUID = 9103361204348868275L;

	public static Pageable getPageable(HttpServletRequest request) {
		Map<String, Object> map = WebUtils.getParamsToMap(request);
		int page = 0;
		if (map.get("page") != null) {
			if (page > 1) {
				page = page - 1;
			}
		}
		int size = 10;
		if (map.get("page") != null) {
			size = Integer.parseInt(map.get("rows").toString());
		}
		return new PageRequest(page, size);
	}

	public PageSupport(int page, int size) {
		super(page, size);
	}

	/**
	 * @param page
	 *            第几页
	 * @param size
	 *            每页的大小
	 * @param sort
	 *            排序
	 */
	public PageSupport(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	public PageSupport(int page, int size, Sort.Direction direction, String... properties) {
		super(page, size, direction, properties);
	}

	public PageSupport(Pageable pageable, Sort sort) {
		super(pageable.getPageNumber(), pageable.getPageSize(), sort);
	}
}
