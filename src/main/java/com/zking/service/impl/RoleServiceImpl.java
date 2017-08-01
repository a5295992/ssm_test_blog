package com.zking.service.impl;

import java.util.List;

import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.commom.ValidateUtils;
import com.zking.dao.RoleMapper;
import com.zking.entity.Role;
import com.zking.entity.RoleExample;
import com.zking.entity.RoleExample.Criteria;
import com.zking.exception.ServiceException;
import com.zking.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private Logger log = Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private Validator valitate;
	@Override
	public void add(Role t) throws ServiceException {
		//验证 数据
		if(!validate(t)){
			roleMapper.insert(t);
		} else {
			throw new ServiceException("2: 角色名已经存在");
		}
	}

	@Override
	public void delete(Object id) {
		
		roleMapper.deleteByPrimaryKey((Integer)id);
	}

	@Override
	public void update(Role t) throws ServiceException {
		//验证 数据
		roleMapper.updateByPrimaryKey(t);
	}

	@Override
	public Role getBean(Object id) {
		return roleMapper.selectByPrimaryKey((Integer) id);
	}

	@Override
	public Page<Role> getPage(QueryCondition queryCondition) {
		RoleExample example = new RoleExample();
		Criteria cri = example.createCriteria();
		if(!StringUtils.isNullOrEmpty(queryCondition.getLikeName())){
			cri.andRoleNameLike(queryCondition.getLikeValue()+"");
		}
		Integer count = (int) roleMapper.countByExample(example);
		
		
		//分組
		if(!StringUtils.isNullOrEmpty(queryCondition.getGroupName())){
			
			log.debug("分組 未實現");
		}
		//排序
		if(!StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
			if(StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
				example.setOrderByClause(queryCondition.getOrderName()+" desc");
			}else{
				example.setOrderByClause(queryCondition.getOrderName()+" "+queryCondition.getOrderValue());
			}
		}
		Page<Role> page = new Page<Role>(queryCondition.getPageCount(),count,queryCondition.getPageNum());
		example.setLimit(page.getStart()+","+page.getPageCount());
		
		
		page.setList(roleMapper.selectByExample(example));
		
		return page;
	}
	
	private  boolean validate(Role t) throws ServiceException{
		String result = ValidateUtils.validate(t,valitate);
		
		if(!result .startsWith("0")){
			throw new ServiceException(result);
		}else{
			
			return nameExits(t);
		}
	}
	//名字是否 存在
	private boolean nameExits(Role t) {
		RoleExample roleExample = new RoleExample();
		
		roleExample.createCriteria().andRoleNameEqualTo(t.getRoleName());
		
		return (roleMapper.selectByExample(roleExample)!=null)?true:false;
	}

	@Override
	public void deleAll(List<Integer> id) {
		RoleExample example = new RoleExample();
		Criteria  cr=  example.createCriteria();
		
		cr.andRoleIdIn(id);
		roleMapper.deleteByExample(example);
	}
}
