package com.zking.service.impl;

import java.util.ArrayList;
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
	public static boolean boolean_getchildren =false;
	
	public static boolean boolean_getmenus =false;
	
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
		
		if(boolean_getmenus){
			cri.andPRidIsNull();
		}
		List<Role> roles= roleMapper.selectByExample(example);
		//是否需要查子集
		if(boolean_getchildren){
			getChildren(roles,getRoles(roles));
		}
		page.setRows(roles);
		return page;
	}
	
	/**
	 * 获取子集
	 * @param roles
	 * @param roles2
	 */
	private void getChildren(List<Role> parents, List<Role> childrens) {
		List<Role> tempList = new ArrayList<Role>();
		for (Role role : parents) {
			List<Role> child = new ArrayList<Role>();
			for (Role crole : childrens) {
				if(role.getRoleId()==crole.getpRid()){
					child.add(crole);
				}else {
					tempList.add(crole);
				}
			}
			if(tempList.size()>0){
				getChildren(child,tempList);
			}
			role.setChildren(child);
		}
	}
	/**
	 * 获取所有角色数据 排除 父级的id 
	 * @param roles2 
	 * @return
	 */
	private List<Role> getRoles(List<Role> roles2) {
		RoleExample example = new RoleExample();
		List<Integer> ids = new ArrayList<Integer>();
		for (Role role : roles2) {
			ids.add(role.getRoleId());
		}
		example.createCriteria().andRoleIdNotIn(ids);
		List<Role> roles= roleMapper.selectByExample(example);
		return roles;
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
		
		return (getRoleByRoleName(t.getRoleName())==null)?false:true;
	}

	@Override
	public int deleAll(List<Integer> id) {
		RoleExample example = new RoleExample();
		Criteria  cr=  example.createCriteria();
		
		cr.andRoleIdIn(id);
		return roleMapper.deleteByExample(example);
	}

	@Override
	public Role getRoleByRoleName(String proleName) {
        RoleExample roleExample = new RoleExample();
		
		roleExample.createCriteria().andRoleNameEqualTo(proleName);
		List <Role> list = roleMapper.selectByExample(roleExample);
		if(list!=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
