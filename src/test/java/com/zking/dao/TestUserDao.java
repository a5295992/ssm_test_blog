package com.zking.dao;

import org.junit.Test;

import com.zking.commom.ContextHodler;

public class TestUserDao {
	
	@Test
	public void testGetUserFlagByUserName(){
		
		UserMapper userMapper = ContextHodler.getBean(UserMapper.class);
		
		Integer flag = userMapper.getFlagByUserName("admin");
		System.out.println(flag);
	}
}
