package com.zking.service;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.User;


public interface BaseService<T> {
	/**
	 * 增
	 * @param t
	 */
	public  void add(T t);
	/**
	 * 删
	 * @param id
	 */
	public  void delete(Object id);
	/**
	 * 改
	 * @param id
	 */
	public void update(T t);
	
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
	public Page<User> getPage( QueryCondition queryCondition);
}
