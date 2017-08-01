package com.zking.service;

import org.junit.Test;

import com.zking.commom.ContextHodler;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.User;

public class TestUserService {
	
	@Test
	public void testGetPage(){
		
		UserService userService = ContextHodler.getBean(UserService.class);
		
		QueryCondition queryCondition = new QueryCondition(0, 10);
		
		Page<User> page = userService.getPage(queryCondition );
		
		if(page!=null){
			for (User  user : page.getList()) {
				System.out.println(user);
			}
		}
		
	}
}
