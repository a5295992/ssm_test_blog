package com.zking.commom;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页条件 内部类 对象
 * 
 * @author Administrator
 */
public class QueryCondition {
	private Integer pageNum; // 当前页
	private Integer pageCount;// 分页条数
	private Map<String, Object> condition;

	public QueryCondition() {
	}
	public void put(String name,Object obj){
		this.condition.put(name, obj);
	}
	public QueryCondition(Integer pageNum, Integer pageCount) {
		this.pageNum = pageNum;
		this.pageCount = pageCount;
		this.condition = new HashMap<String, Object>();
	}
	public QueryCondition(Integer pageNum, Integer pageCount,
			Map<String, Object> condition) {
		super();
		this.pageNum = pageNum;
		this.pageCount = pageCount;
		this.condition = condition;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

}

