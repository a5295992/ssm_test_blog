package com.zking.service.impl;

import java.util.List;

import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.commom.ValidateUtils;
import com.zking.dao.UserMapper;
import com.zking.entity.User;
import com.zking.entity.UserExample;
import com.zking.entity.UserExample.Criteria;
import com.zking.exception.ServiceException;


@Service
public class UserServiceImpl implements com.zking.service.UserService {
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Validator validate;
	
	
	@Override
	public void add(User t)  throws ServiceException{
		
		String result  = ValidateUtils.validate(t, validate);
		if(result .startsWith("0")){
			if(nameExist(t)){
				userMapper.insert(t);
			}else{
				throw new ServiceException(fault+"用户名重复");
			}
		}else{
			throw new ServiceException(result);
		}
	}
	/**
	 * 唯一约束检测
	 * @param t
	 * @return
	 */
	private boolean nameExist(User t) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNameEqualTo(t.getUserName());
		return userMapper.selectByExample(userExample)!= null?true:false;
	}

	@Override
	public void delete(Object id) throws ServiceException{
		userMapper.deleteByPrimaryKey((Integer)id);
		
	}

	@Override
	public void update(User t) throws ServiceException {
		userMapper.updateByPrimaryKey(t);
		
	}
	
	@Override
	public User getBean(Object id) {
		
		return userMapper.selectByPrimaryKey((Integer) id);
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

	@Override
	public void deleAll(List<Integer> id) {
		UserExample example = new UserExample();
		Criteria  cr=  example.createCriteria();
		
		cr.andUserIdIn(id);
		userMapper.deleteByExample(example);
	}
}
