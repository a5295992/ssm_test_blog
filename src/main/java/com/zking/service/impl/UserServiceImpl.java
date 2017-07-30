package com.zking.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.dao.UserMapper;
import com.zking.entity.User;
import com.zking.entity.UserExample;


@Service
public class UserServiceImpl implements com.zking.service.UserService {
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Override
	public void add(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public User getBean(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getPage(QueryCondition queryCondition) {
		
		//获取 总记录数
		UserExample userExample = new UserExample();
		Integer count = (int) userMapper.countByExample(userExample);
		log.info(" 总记录数" +count);
		//创建分页对象
		Page<User> page = new Page<User>(queryCondition.getPageCount(),count,queryCondition.getPageNum());
		UserExample userExample2 = new UserExample();
		userExample2.setLimit(page.getStart()+","+page.getPageCount());
		List<User> userList = userMapper.selectByExample(userExample2);
		page.setList(userList);
		
		return page;
	}
	
	
}
