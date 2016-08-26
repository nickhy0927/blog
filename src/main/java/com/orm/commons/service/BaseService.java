package com.orm.commons.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.ObjectTools;

public abstract interface BaseService<E, ID extends Serializable> {

	public abstract void delete(ID id) throws ServiceException;

	public abstract void delete(Iterable<E> paramIterable) throws ServiceException;

	public abstract void deleteAll() throws ServiceException;

	public abstract void deleteBatch(String[] ids) throws ServiceException;

	abstract List<E> findAll() throws ServiceException;

	public abstract E get(ID id) throws ServiceException;

	public abstract List<E> queryByMap(Map<String, Object> paramMap) throws ServiceException;

	public abstract List<E> queryByMap(Map<String, Object> paramMap, Sort sort) throws ServiceException;

	public abstract Page<E> queryPageByMap(Map<String, Object> paramMap, Pageable paramPageable) throws ServiceException;

	public abstract ObjectTools<E> queryPageByMap(Map<String, Object> map, String currentPage, Sort sort) throws ServiceException;

	public abstract E save(E entity) throws ServiceException;
}