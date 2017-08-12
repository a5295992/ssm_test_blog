package com.zking.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.dao.BlogDataMapper;
import com.zking.dao.BlogMapper;
import com.zking.entity.Blog;
import com.zking.entity.BlogData;
import com.zking.entity.BlogDataExample;
import com.zking.entity.BlogExample;
import com.zking.entity.BlogExample.Criteria;
import com.zking.exception.ServiceException;
import com.zking.service.BlogService;
@Service
public class BlogServiceImpl implements BlogService {
	
	
    @Autowired
	private BlogMapper blogMapper;
    @Autowired
    private BlogDataMapper blogDateMapper;
	@Override
	//开启事务
	
	public void add(Blog t) throws ServiceException {
		//验证 是否同名
		if(nameDoNotExist(t)){
			blogMapper.insert(t);
		}else {
			//同名
			throw new ServiceException(fault+"标题重名");
		}
	}
    
	private boolean nameDoNotExist(Blog t) {
		List<Blog> list = findByName(t.getTitle(),t.getCreateBy());
		if(list!=null&&list.size()>0){
			return false;
		}else{ 
			return true;
		}
	}
	public List<Blog> findByName(String title,Integer userId) {
		BlogExample example = new BlogExample();
		example.createCriteria().andTitleEqualTo(title)
		.andCreateByEqualTo(userId);
		return blogMapper.selectByExample(example);
	}
	
	@Transactional
	@Override
	public void delete(Object id) throws ServiceException {
		Blog blog = getBean(id);
		Integer dataId = null;
		if(blog!=null){
			dataId = blog.getDataId();
		}
		blogDateMapper.deleteByPrimaryKey(dataId );
		blogMapper.deleteByPrimaryKey((Integer)id);
	}
	
	@Transactional(rollbackFor=SQLException.class)
	@Override
	public int deleAll(List<Integer> id) {
		BlogDataExample example1 = new BlogDataExample();
		example1.createCriteria().andDataIdIn(id);
		blogDateMapper.deleteByExample(example1);
		
		BlogExample example = new BlogExample();
		example.createCriteria().andBlogIdIn(id);
		
		return blogMapper.deleteByExample(example);
	}

	@Override
	public void update(Blog t) throws ServiceException {
		blogMapper.updateByPrimaryKey(t);

	}

	@Override
	public Blog getBean(Object id) {
		return blogMapper.selectByPrimaryKey((Integer)id);
	}

	@Override
	public Page<Blog> getPage(QueryCondition queryCondition) {
		
		BlogExample example = new BlogExample();
		Criteria cri = example.createCriteria();
		if(!StringUtils.isNullOrEmpty(queryCondition.getLikeName())){
			cri.andTitleLike(queryCondition.getLikeValue()+"");
		}
		
		//条件 Equals
		if(queryCondition.getCondition()!=null){
			//userId
			if(queryCondition.getCondition().containsKey(Blog.CREATEBY)){
				cri.andCreateByEqualTo((Integer)queryCondition.getCondition().get(Blog.CREATEBY));
			};
		}
		
		Integer count = (int) blogMapper.countByExample(example);
		
		//排序
		if(!StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
			if(StringUtils.isNullOrEmpty(queryCondition.getOrderName())){
				example.setOrderByClause(queryCondition.getOrderName()+" desc");
			}else{
				example.setOrderByClause(queryCondition.getOrderName()+" "+queryCondition.getOrderValue());
			}
		}
		
		Page<Blog> page = new Page<Blog>(queryCondition.getPageCount(),count,queryCondition.getPageNum());
		example.setLimit(page.getStart()+","+page.getPageCount());
		
		List<Blog> blogs= blogMapper.selectByExample(example);
		
		page.setRows(blogs);
		return page;
	}

	@Transactional(rollbackFor=ServiceException.class)
	@Override
	public void insertBlogAndDate(Blog blog, BlogData blogDate) throws ServiceException {
		Integer id = new Random().nextInt(10000000);
		 blog.setBlogId(id);
		 blog.setDataId(id);
		 blogDate.setDataId(id);
		 blogDateMapper.insert(blogDate);
		 add(blog);
	}
}
