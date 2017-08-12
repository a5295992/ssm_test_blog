package com.zking.service;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.BlogCategory;
import com.zking.exception.ServiceException;

public class TestBlogCateGoryService {
	private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	private BlogCategoryService blogCategoryService;
	
//    @Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		blogCategoryService = app.getBean(BlogCategoryService.class);
	}
//    @Test
    public void testAdd() throws ServiceException{
    	BlogCategory t = new BlogCategory();
    	
    	String categoryName = "心得2";
    	Integer deleFlag = 0;
		t.setCategoryName(categoryName );
		t.setDeleFlag(deleFlag);
    	blogCategoryService.add(t);
    }
//    @Test
    public void testDele() throws ServiceException{
    	blogCategoryService.deleAll(Arrays.asList(1,2,3));
    }
//    @Test
    public void testQuery(){
    	Integer id = 4;
    	BlogCategory c = blogCategoryService.getBean(id);
    	System.out.println(c);
    }
    
//    @Test
    public void testQuerys(){
    	QueryCondition queryCondition = new QueryCondition(0,10);
    	queryCondition.setLikeName("22");
    	queryCondition.setLikeValue("%心%");
    	Page<BlogCategory> page = blogCategoryService.getPage(queryCondition);
    	
    	System.out.println(page.getRows());
    }
}
