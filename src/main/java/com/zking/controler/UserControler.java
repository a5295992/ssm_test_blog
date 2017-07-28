package com.zking.controler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.entity.User;
@Controller
public class UserControler {
	private Logger log = Logger.getLogger(UserControler.class);
	
	/**
	 * 前台登录 /login
	 * @return
	 *views/ ..jsp
	 */
	@RequestMapping(AnRequest.LOGIN)
	public String toLogin(){
		return LocationConstants.LOGIN;
	}
	/**
	 * 后台登录 /login
	 * @return
	 *views/ ..jsp
	 */
	@RequestMapping(AnRequest.ALOGIN)
	public String toALogin(){
		return LocationConstants.ALOGIN;
	}
	@RequestMapping(AnRequest.AJAXLOGIN)
	public String login(User user){
		String message = "";
		return message ;
	}
}
