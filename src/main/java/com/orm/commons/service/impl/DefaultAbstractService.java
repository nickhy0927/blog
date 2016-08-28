package com.orm.commons.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.orm.commons.exception.ServiceException;
import com.orm.commons.service.BaseService;
import com.orm.commons.service.CakoHyJpaRepostiory;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.PageSupport;
import com.orm.commons.utils.Pager;

@Transactional(readOnly = true)
public abstract class DefaultAbstractService<E, ID extends Serializable> implements BaseService<E, ID> {

	@Autowired
	private CakoHyJpaRepostiory<E, ID> dao;

	@Override
	@Transactional(readOnly = false)
	public void delete(ID id) throws ServiceException {
		E t = get(id);
		if (t != null) {
			this.dao.delete(id);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Iterable<E> entities) throws ServiceException {
		this.dao.delete(entities);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAll() throws ServiceException {
		this.dao.deleteAll();
	}

	@Transactional(readOnly = false)
	@Override
	@SuppressWarnings("unchecked")
	public void deleteBatch(String[] ids) throws ServiceException {
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				delete((ID) id);
			}
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> findAll() throws ServiceException {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public E get(ID id) throws ServiceException {
		return this.dao.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> queryByMap(Map<String, Object> map) throws ServiceException {
		return this.dao.queryByMap(map);
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> queryByMap(Map<String, Object> paramMap, Sort sort) throws ServiceException {
		return dao.queryByMap(paramMap, sort);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<E> queryPageByMap(Map<String, Object> map, Pageable pageable) throws ServiceException {
		return this.dao.queryPageByMap(map, pageable);
	}

	@Override
	public ObjectTools<E> queryPageByMap(Map<String, Object> map, String currentPage, Sort sort)
			throws ServiceException {
		List<E> list = queryByMap(map);
		Object pageSizeObj = map.get("pageSize");
		Pager pager = new Pager(list.size(), currentPage, pageSizeObj);
		int page = 0;
		if (pager.getCurrentPage() - 1 >= 0) {
			page = pager.getCurrentPage() - 1;
		}
		PageSupport pageable = new PageSupport(page, pager.getPageSize(), sort);
		map.remove("pageSize");
		Page<E> pageInfo = queryPageByMap(map, pageable);
		ObjectTools<E> object = new ObjectTools<E>();
		object.setEntities(pageInfo.getContent());
		object.setPager(pager);
		return object;
	}

	@Override
	@Transactional(readOnly = false)
	public E save(E entity) throws ServiceException {
		return this.dao.saveEntity(entity);
	}
}