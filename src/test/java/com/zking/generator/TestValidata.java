package com.zking.generator;

import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.entity.User;

public class TestValidata {
	
	private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	@Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		
	}
	@Test
	public void testValidata(){
		
		User user = new User();
		user.setUserName(null);
		
		System.out.println(app);
		
		Validator v  = app.getBean("validator",Validator.class);
		System.out.println(v);
		System.out.println(v.validate(user).isEmpty());
		
	}
}
