package com.zking.service;

import com.zking.entity.Blog;
import com.zking.entity.BlogData;
import com.zking.exception.ServiceException;

public interface BlogService  extends BaseService<Blog>{
  
	
	/**
	 * 添加 博客 和数据 
	 * @param blog
	 * @param blogDate
	 * @throws ServiceException 
	 */
	public void insertBlogAndDate(Blog blog ,BlogData blogDate) throws ServiceException;
}
