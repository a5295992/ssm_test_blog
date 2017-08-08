package com.zking.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.Permission;
import com.zking.exception.ServiceException;

public class TestPermissionService {
 
	
    private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	private PermissionService permissionService;
	
//	@Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		permissionService = app.getBean(PermissionService.class);
	}
	
//	@Test
	public void testGetBeans(){
		
		QueryCondition queryCondition = new QueryCondition(0,10);
		queryCondition.setLikeName("per_name");
		queryCondition.setLikeValue("%菜单%");
		Page<Permission> pege = permissionService.getPage(queryCondition );
		
		for (Permission iterable_element : pege.getList()) {
			System.out.println(iterable_element.getPerName());
			System.out.println(iterable_element.getRoleName()+":getRoleName");
		}
	}
//	@Test
	public void testAddMermision() throws ServiceException{
		
		Permission t = new Permission();
		t.setPerName("2323");
		t.setRoleId(1);
		t.setDeleFlag(0);
		permissionService.add(t );
	}
}
