package com.zking.commom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextHodler {
	private static final String contextPath = "classpath:spring-context.xml";
	
	
	private static ApplicationContext app;
	static
	{
		app =new ClassPathXmlApplicationContext(new String[]{contextPath});
	}
	public static <T> T getBean(Class<T>  clazz){
		
		return app.getBean(clazz);
	}
	
}
