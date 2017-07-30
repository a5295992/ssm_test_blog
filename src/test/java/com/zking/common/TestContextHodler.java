package com.zking.common;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestContextHodler {
	
	private String  contextPath = "classpath:spring-context.xml";
	private ClassPathXmlApplicationContext app;
	
	public TestContextHodler(){
		app = new ClassPathXmlApplicationContext(contextPath);
	}
	
	public <T> T getBean(Class<T> t){
		
		return app.getBean(t);
	}
	
	@Test
	public void test(){
		
		
//		CacheUtils.putOnCache("niubi", "along", "三岁");
//		
//		System.out.println(CacheUtils.getCacheByName("niubi", "along"));
//		
		/*
		CacheUtils.putOnCache("niubi", "222", new User());
		*/
	//	System.out.println(CacheUtils.getCacheByName("niubi", "222"));
//		CacheUtils.removeCache("niubi");
	}
}
