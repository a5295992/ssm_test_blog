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
import com.zking.dao.PermissionMapper;
import com.zking.entity.Permission;
import com.zking.entity.PermissionExample;
import com.zking.entity.PermissionExample.Criteria;
import com.zking.exception.ServiceException;
import com.zking.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private Validator valitate;
	private Logger log = Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public void add(Permission t) throws ServiceException {
		String result = ValidateUtils.validate(t,valitate);
		if(result.startsWith("0")){
			if(!namesExist(t)){
				permissionMapper.insert(t);
			}
			throw new ServiceException(fault+"权限已存在");
		}else{
			throw new ServiceException(result);
		}
	}
	/**
	 * 唯一 约束检查
	 * @param t
	 * @return
	 */
	private boolean namesExist(Permission t) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andPerNameEqualTo(t.getPerName());
		return permissionMapper.selectByExample(example)!=null?true:false;
	}

	@Override
	public void delete(Object id) {
		permissionMapper.deleteByPrimaryKey((Integer)id);
	}

	@Override
	public void update(Permission t) {
		
		permissionMapper.updateByPrimaryKey(t);
	}

	@Override
	public Permission getBean(Object id) {
		return permissionMapper.selectByPrimaryKey((Integer)id);
	}

	@Override
	public Page<Permission> getPage(QueryCondition queryCondition) {
		PermissionExample example = new PermissionExample();
		Criteria cri = example.createCriteria();
		if(!StringUtils.isNullOrEmpty(queryCondition.getLikeName())){
			cri.andPerNameLike(queryCondition.getLikeValue()+"");
		}
		Integer count = (int) permissionMapper.countByExample(example);
		
		
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
		Page<Permission> page = new Page<Permission>(queryCondition.getPageCount(),count,queryCondition.getPageNum());
		example.setLimit(page.getStart()+","+page.getPageCount());
		
		
		page.setList(permissionMapper.selectByExample(example));
		
		return page;
	}
	
	
	@Override
	public void deleAll(List<Integer> id) {
		PermissionExample example = new PermissionExample();
		Criteria  cr=  example.createCriteria();
		
		cr.andPerIdIn(id);
		permissionMapper.deleteByExample(example);
	}
}
