package com.zking.service.impl;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zking.commom.CacheUtils;
import com.zking.commom.MD5Encode;
import com.zking.commom.UserUtils;
import com.zking.dao.UserMapper;
import com.zking.exception.AuthFailException;
import com.zking.service.LoginService;
/**
 * 登录 service 实现类
 * 
 * @author Administrator
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final String DEFAULT_REMEMBERME = "rememberMe";

	private String rememberMe = DEFAULT_REMEMBERME;

	private Logger log = Logger.getLogger(LoginServiceImpl.class);
	
	private String userCache = "userCache";

	@Autowired
	private UserMapper userMapper;// 用户 mapper

	private ServletRequest request;

	/**
	 * 登录操作
	 * 
	 * @param userName
	 *            用户名，
	 * @param password
	 *            密码
	 * @param request
	 *            HttpServletRequest 用来获取 重要http参数
	 * @throws AuthFailException
	 */

	public void login(String userName, String password,
			HttpServletRequest request) throws AuthFailException {
		this.request = request;
		// @RequeiredParm ? 此处 需要验证?
		// 1.验证 用户名 密码
		// 验证用户是否可用
		Integer flag = 0;
		flag = userMapper.getFlagByUserName(userName);

		if (flag == null) {
			throw new AuthFailException("无此用户，请核对账号");
		} else {
			if (flag == 0) {
				boolean isRememberme = rememberMe();// 记住我
				String host = request.getRemoteAddr();// Ip
				UsernamePasswordToken token = new UsernamePasswordToken(
						userName, MD5Encode.encode(password), isRememberme, host);
				
				Subject subject = SecurityUtils.getSubject();

				try {
					subject.login(token);
				} catch (AuthenticationException e) {
					log.info("msg : 授权 失败");
					throw new AuthFailException("用户名或密码错误");
				}
			} else {
				throw new AuthFailException("该用户账号处于封闭状态！联系管理员");
			}
		}

	}

	private boolean rememberMe() {
		try {
			Boolean isRemember = (Boolean) request.getAttribute(rememberMe);
			return (boolean) isRemember;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * 退出 当前账号
	 * @param subject 当前用户
	 * 
	 */
	public void logout(Subject subject) {
		try {
			subject.logout();
			subject.getSession().stop();
			CacheUtils.removeCache(userCache,UserUtils.getUser().getUserName());
		} catch (Exception e) {
			log.error(e);
		}
	}
}
