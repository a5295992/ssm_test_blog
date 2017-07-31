package com.zking.commom;

import java.util.List;
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
	
	//自定义 查询条件 通过表单提交
	private List<String> equalName; //可能有多个 
	private List<Object> equalValue;
	
	private String likeName;
	private Object likeValue; //模糊查询
	
	private String orderName;
	private Object orderValue;//排序
	
	private String groupName;
	
	private String countName; //统计
	
	private String inName;    //在某个范围
	private List<Object> inValue;
	
	
	public List<String> getEqualName() {
		return equalName;
	}
	public void setEqualName(List<String> equalName) {
		this.equalName = equalName;
	}
	public List<Object> getEqualValue() {
		return equalValue;
	}
	public void setEqualValue(List<Object> equalValue) {
		this.equalValue = equalValue;
	}
	public String getLikeName() {
		return likeName;
	}
	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}
	public Object getLikeValue() {
		return likeValue;
	}
	public void setLikeValue(Object likeValue) {
		this.likeValue = likeValue;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Object getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Object orderValue) {
		this.orderValue = orderValue;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		this.countName = countName;
	}
	public String getInName() {
		return inName;
	}
	public void setInName(String inName) {
		this.inName = inName;
	}
	public List<Object> getInValue() {
		return inValue;
	}
	public void setInValue(List<Object> inValue) {
		this.inValue = inValue;
	}
	public QueryCondition() {
		
	}
	public void put(String name,Object obj){
		this.condition.put(name, obj);
	}
	public QueryCondition(Integer pageNum, Integer pageCount) {
		this.pageNum = (pageNum==null)?0:pageNum;
		this.pageCount = (pageCount==null||pageCount==0)?10:pageCount;
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

