package com.zking.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.dao.BlogDataMapper;
import com.zking.entity.BlogData;
import com.zking.entity.BlogDataExample;
import com.zking.exception.ServiceException;
import com.zking.service.BlogDataService;


@Service
public class BlogDataServiceImpl implements BlogDataService {
    @Autowired
	private BlogDataMapper blogDataMapper;
	@Override
	public void add(BlogData t) throws ServiceException {
		blogDataMapper.insert(t);
	}

	@Override
	public void delete(Object id) throws ServiceException {

		blogDataMapper.deleteByPrimaryKey((Integer)id);

	}

	@Override
	public int deleAll(List<Integer> id) {
		BlogDataExample example = new BlogDataExample();
		example.createCriteria().andDataIdIn(id);
		return blogDataMapper.deleteByExample(example);
	}

	@Override
	public void update(BlogData t) throws ServiceException {

		blogDataMapper.updateByPrimaryKey(t);
	}

	@Override
	public BlogData getBean(Object id) {
		
		return blogDataMapper.selectByPrimaryKey((Integer)id);
	}

	@Override
	public Page<BlogData> getPage(QueryCondition queryCondition) {
		return null;
	}

}
