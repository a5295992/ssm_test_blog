package com.zking.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zking.commom.DateUtil;
import com.zking.commom.JSONutil;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.entity.Blog;
import com.zking.entity.BlogData;
import com.zking.exception.ServiceException;

public class TestBlogService {
	private String path = "spring-context.xml";
	
	private ClassPathXmlApplicationContext app;
	
	private BlogService blogService;
	
//    @Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(path);
		blogService = app.getBean(BlogService.class);
	}
//    @Test
    public void testInsert() throws ServiceException{
    	Blog blog =new Blog();
    	blog.setTitle("好好学习 Java 成就高薪梦想1");
    	blog.setBlogDesc("如何学习java十三点看法");
    	blog.setCategoryId(4);
    	blog.setCreateTime(DateUtil.toSqlDate());
    	blog.setDeleFlag(0);
    	blog.setCreateBy(1);
    	
		BlogData blogDate = new BlogData();
		blogDate.setContent("1.扫描包"
				+"2.示图解析器"
				+"InternalResourceViewResolver"
				+"qwe5295992   旧南城以南"
				+"aaa975914899"
				+"qwe2303829   旧南城以北 敏金"
				+"aaa975914899"
				+"qwer5295992  孤独袖木 敏土 或敏水"
				+"a5295992"
				+"asd2303829   南帝北丐 敏木"
				+"aaa975914899"
				+"asdf5295992  李沧海   慢木"
				+"a5295992"
				+""
				+"zxcv5295992  逆天残刀  法火"
				+"a5295992)");
		blogService.insertBlogAndDate(blog, blogDate);
    }
    
//    @Test
    public void testSelect() throws ServiceException{
		Integer id = 0;
		Blog blog = blogService.getBean(id);
		System.out.println(blog);
    }
    
//    @Test
    public void testUpdate() throws ServiceException{
    	Blog t = blogService.getBean(0);
    	t.setTitle("哥现在就把标题改了,你能把我怎样");
    	blogService.update(t);
    }
//    @Test
    public void testdelete() throws ServiceException{
    	
    	blogService.delete(0);
    }
    
//    @Test
    public void testdeleteAll() throws ServiceException{
    	
    	blogService.deleAll(Arrays.asList(2047860,8632998,0));
    }
    
//    @Test
    public  void testToJoson(){

		QueryCondition queryCondition  =   new QueryCondition(1,10);//
		Map<String,Object> map         =   new HashMap<String,Object>();
			map.put(Blog.CREATEBY,         1);
				queryCondition.setCondition(map);
		Page<Blog>     pager 		   =   blogService.getPage(queryCondition  );//
		JSONObject     json  		   =   JSONutil.formatJsonBeanDate(pager);
		
		System.out.println(json);
    }
}
