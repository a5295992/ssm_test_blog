package com.zking.security.entity;

import java.io.Serializable;
/**
 * 当前登录对象
 * @author Administrator
 *
 */
public class ShiroUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String userCname;
	
	private Integer userId;
	
	private String host;
	
	private Integer roleId;
	
	public ShiroUser(){}
	
	public ShiroUser(String userName,String userCname,Integer userId,String host ,Integer roleId){
		
		this.userName = userName;
		this.userCname = userCname;
		this.userId=userId;
		this.host=host;
		this.roleId=roleId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCname() {
		return userCname;
	}

	public void setUserCname(String userCname) {
		this.userCname = userCname;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	private String sessionId;
}
