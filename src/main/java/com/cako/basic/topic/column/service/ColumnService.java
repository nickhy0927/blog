/**
 *
 */
package com.cako.basic.topic.column.service;

import com.cako.basic.topic.column.entity.Column;
import com.orm.commons.service.BaseService;

/**
 * @描述：
 * @author HUANGYUAN
 * @TIME:2015年9月13日 下午12:32:25
 */
public interface ColumnService extends BaseService<Column, String> {

	public Column saveColumn(Column column);
}
