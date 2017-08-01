package com.zking.service;

import java.util.List;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.exception.ServiceException;


public interface BaseService<T> {
	
	public String success ="0:";
	public String fault   = "1:";
	public String other   = "3:";
	/**
	 * 增
	 * @param t
	 * @throws ServiceException 
	 */
	public  void add(T t) throws ServiceException;
	/**
	 * 删
	 * @param id
	 */
	public  void delete(Object id) throws ServiceException;
	/**
	 * 删除 所有
	 * @param id
	 */
	public void deleAll(List<Integer> id);
	/**
	 * 改
	 * @param id
	 */
	public void update(T t) throws ServiceException;
	
	/**
	 * 查 单个
	 * @param id
	 */
	public T getBean(Object id);
	
	/**
	 * 根据条件 获取 分页对象
	 * @param queryCondition
	 * @return
	 */
	public Page<T> getPage( QueryCondition queryCondition);
}
