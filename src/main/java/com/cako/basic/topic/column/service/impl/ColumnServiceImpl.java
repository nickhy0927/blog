package com.cako.basic.topic.column.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cako.basic.topic.column.dao.ColumnDao;
import com.cako.basic.topic.column.entity.Column;
import com.cako.basic.topic.column.service.ColumnService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
public class ColumnServiceImpl extends DefaultAbstractService<Column, String> implements ColumnService {

	@Autowired
	private ColumnDao columnDao;

	@Override
	public Column saveColumn(Column column) {
		return columnDao.saveAndFlush(column);
	}

}
