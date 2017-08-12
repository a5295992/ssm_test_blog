package com.zking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.dao.BlogCategoryMapper;
import com.zking.entity.BlogCategory;
import com.zking.entity.BlogCategoryExample;
import com.zking.entity.BlogCategoryExample.Criteria;
import com.zking.exception.ServiceException;
import com.zking.service.BlogCategoryService;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    
	@Autowired
	private BlogCategoryMapper blogCategoryMapper;
	@Override
	public void add(BlogCategory t) throws ServiceException {
		
		if(nameDoNotExist(t)){
			blogCategoryMapper.insert(t);
		}else {
			//同名
			throw new ServiceException(fault+"分类重名");
		}
	}
	private boolean nameDoNotExist(BlogCategory t) {
		List<BlogCategory> list = findByName(t.getCategoryName());
		if(list!=null&&list.size()>0){
			return false;
		}else{
			return true;
		}
	}
	public List<BlogCategory> findByName(String catagoryName) {
		BlogCategoryExample example = new BlogCategoryExample();
		example.createCriteria().andCategoryNameEqualTo(catagoryName);
		return blogCategoryMapper.selectByExample(example);
	}

	@Override
	public void delete(Object id) throws ServiceException {
		blogCategoryMapper.deleteByPrimaryKey((Integer)id);
	}

	@Override
	public int deleAll(List<Integer> id) {
		BlogCategoryExample example = new BlogCategoryExample();
		example.createCriteria().andCategoryIdIn(id);
		
		return blogCategoryMapper.deleteByExample(example);
	}

	@Override
	public void update(BlogCategory t) throws ServiceException {
		blogCategoryMapper.updateByPrimaryKey(t);
	}

	@Override
	public BlogCategory getBean(Object id) {
		return blogCategoryMapper.selectByPrimaryKey((Integer)id);
	}

	@Override
	public Page<BlogCategory> getPage(QueryCondition queryCondition) {
		BlogCategoryExample example = new BlogCategoryExample();
		Criteria cri = example.createCriteria();
		if(!StringUtils.isNullOrEmpty(queryCondition.getLikeName())){
			cri.andCategoryNameLike(queryCondition.getLikeValue()+"");
		}
		Integer count = (int) blogCategoryMapper.countByExample(example);
		
		//排序
		if(!StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
			if(StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
				example.setOrderByClause(queryCondition.getOrderName()+" desc");
			}else{
				example.setOrderByClause(queryCondition.getOrderName()+" "+queryCondition.getOrderValue());
			}
		}
		Page<BlogCategory> page = new Page<BlogCategory>(queryCondition.getPageCount(),count,queryCondition.getPageNum());
		example.setLimit(page.getStart()+","+page.getPageCount());
		
		List<BlogCategory> blogs= blogCategoryMapper.selectByExample(example);
		
		page.setRows(blogs);
		return page;
	}

}
