package com.zking.service;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.commom.QueryCondition;
import com.zking.entity.Role;
import com.zking.exception.ServiceException;
import com.zking.service.impl.RoleServiceImpl;

public class TestRoleService {
private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	private RoleService roleService;
	
	@Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		roleService = app.getBean(RoleService.class);
	}
	
//	@Test
	public void testGetRoleBeans(){
		
		QueryCondition queryCondition = new QueryCondition(0,10);
		RoleServiceImpl.boolean_getmenus=true;
		RoleServiceImpl.boolean_getchildren=true;
		List <Role> list = roleService.getPage(queryCondition).getList();
		
		for (Role role : list) {
			System.out.println(role.getText());
			if(role.getChildren()!=null){
				for (Role role2 : role.getChildren()) {
					System.out.println(role.getText()+"子集" +role2.getText());
				}
			}
			
		}
		/*JSONArray json  = JSONArray.fromObject(roleService.toStandardTreeEntity(list));
		
		System.out.println(json);*/
		
//		String a = SSMJSONUtils.changeEntityToStandardTreeEntity(list, "roleId", "roleName", "children").toString();
		
		//System.out.println(roleService.getPage(queryCondition).getList().get(0).getChildren().get(0).getRoleName());
	}
	
//	@Test
	public void add() throws ServiceException{
		
		Role role = new Role();
		role.setRoleName("admin");
		role.setDeleFlag(0);
		roleService.add(role);
	}
//	@Test
	public void dele() throws ServiceException{
		
		roleService.deleAll(Arrays.asList(8,9,10,11,12));
	}
	
//	@Testo
	public void update() throws ServiceException{
		Role role = new Role();
		role.setRoleId(13);
		role.setRoleName("老宋");
		role.setDeleFlag(0);
		roleService.update(role);
	}
	
	@Test
	public void testGetRoleByName(){
		
		QueryCondition queryCondition = new QueryCondition(0,10);
		RoleServiceImpl.boolean_getmenus=true;
		RoleServiceImpl.boolean_getchildren=true;
		List <Role> list = roleService.getPage(queryCondition).getList();
		
		for (Role role : list) {
			System.out.println(role.getText());
			if(role.getChildren()!=null){
				for (Role role2 : role.getChildren()) {
					System.out.println(role.getText()+"子集" +role2.getText());
				}
			}
			
		}
		/*JSONArray json  = JSONArray.fromObject(roleService.toStandardTreeEntity(list));
		
		System.out.println(json);*/
		
//		String a = SSMJSONUtils.changeEntityToStandardTreeEntity(list, "roleId", "roleName", "children").toString();
		
		//System.out.println(roleService.getPage(queryCondition).getList().get(0).getChildren().get(0).getRoleName());
	}
}
