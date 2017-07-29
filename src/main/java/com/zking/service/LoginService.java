package com.zking.service;

import javax.servlet.http.HttpServletRequest;

import com.zking.exception.AuthFailException;

public interface LoginService {

	/**
	 * 登录操作
	 * @param userName 用户名，
	 * @param password 密码
	 * @param request  HttpServletRequest 用来获取 重要http参数
	 * @throws AuthFailException 
	 */
	void login(String userName, String password,
			HttpServletRequest request) throws AuthFailException;

	
}
