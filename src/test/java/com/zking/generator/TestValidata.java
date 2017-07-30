package com.zking.generator;

import javax.validation.Validator;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.User;
import com.zking.service.UserService;

public class TestValidata {
	
	private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		
	}
	public void testValidata(){
		
		User user = new User();
		user.setUserName(null);
		
		System.out.println(app);
		
		Validator v  = app.getBean("validator",Validator.class);
		System.out.println(v);
		System.out.println(v.validate(user).isEmpty());
		
	}
	public void testGet$(){
		UserService userService = app.getBean(UserService.class);
		
		QueryCondition queryCondition = new QueryCondition(0, 1);
		
		Page<User> page = userService.getPage(queryCondition );
		
		if(page!=null){
			for (User  user : page.getList()) {
				System.out.println(user);
			}
		}
	}
}
