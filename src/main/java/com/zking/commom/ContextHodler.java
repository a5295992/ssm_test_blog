package com.zking.commom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service
public class ContextHodler implements ApplicationContextAware,
DisposableBean {
	
	private static ApplicationContext app;
	static
	{
	}
	public static <T> T getBean(Class<T>  clazz){
		
		return app.getBean(clazz);
	}
	
	
	
	@Override
	public void destroy() throws Exception {
		ContextHodler.clearHolder();
		
	}
	private static void clearHolder() {
		app=null;
	}



	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		ContextHodler.app=applicationContext;
	}
	
}
