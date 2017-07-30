package com.zking.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zking.service.UserService;
/**
 * 用户 数据统一访问URL
 * @author Along
 * @Date 2017-7-30
 */
@Controller
public class UserControler {
	
	@Autowired
	private UserService userService;//用户service
}
