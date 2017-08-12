package com.zking.controler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zking.commom.DateUtil;
import com.zking.commom.JSONutil;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.commom.UserUtils;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.entity.Blog;
import com.zking.entity.BlogCategory;
import com.zking.entity.BlogData;
import com.zking.exception.ServiceException;
import com.zking.service.BlogCategoryService;
import com.zking.service.BlogDataService;
import com.zking.service.BlogService;

/**
 * 博客 管理数据接口
 * @author ALong
 */
@Controller
@RequestMapping(value=AnRequest.A_BLOG)
public class BlogController extends BaseController {
    private static Logger log = Logger.getLogger(BlogController.class);
    
    @Autowired
    private BlogService bolgService;
    @Autowired
    private BlogCategoryService bolgCategoryService;
    @Autowired
    private BlogDataService blogDataService;
    
	//  目录
	//1.to_blog_edit_view              博客编辑页面           A_BLOG_EDIT 	               /a/blog/new/edit
	//2.do_blog_edit_add               添加博客信息           A_BLOG_EDIT_ADD                /a/blog/new/add
    //3.to_blog_query                  浏览所有博客           A_BLOG_QUERY                   /a/blog/query
    //4.do_blog_edit_update            更新指定博客           A_BLOG_EDIT_UPDATE             /a/blog/edit
    //5.do_blog_edit_delete            删除指定博客           A_BLOG_EDIT_DELETE             /a/blog/delete
    
    //3.to_blog_category_manage        博客分类管理           A_BLOG_CATEGORY_MANAGE         /a/blog/category/manage
    //4.do_blog_category_manage_add    博客分类管理           A_BLOG_CATEGORY_MANAGE_ADD     /a/blog/category/add
    //5.do_blog_category_manage_dele   博客分类管理           A_BLOG_CATEGORY_MANAGE_DELE    /a/blog/category/dele
    //5.do_blog_category_manage_update 博客分类管理           A_BLOG_CATEGORY_MANAGE_UPDATE  /a/blog/category/update
    //6.to_blog_category_manage_query  博客分类管理           A_BLOG_CATEGORY_MANAGE_QUERY   /a/blog/category/query
	@RequestMapping(value=AnRequest.A_BLOG_NEW_EDIT)
	public ModelAndView to_blog_edit_view(){
		return new ModelAndView(LocationConstants.A_BLOG_EDIT);
	}
	
	@ResponseBody
	@RequestMapping(value=AnRequest.A_BLOG_EDIT_ADD,
	produces = "text/html;charset=UTF-8;",
    method=RequestMethod.POST)
	public String do_blog_edit_add(HttpServletRequest req){
		String data = req.getParameter(DO_BLOG_EDIT_ADD_AJAXDATA);
		
		JSONObject json        =   JSONObject.fromObject(data);//
		Integer blogId = null;
		try {
			blogId = Integer.parseInt(json.getString(Blog.BLOGID));
		} catch (Exception e1) {
			log.info("blogId"+blogId);
		}
		String title = null;
		try {
			title = json.getString(Blog.TITLE);
		} catch (Exception e1) {
			return fault+"标题名为空";
		}
		Integer cateGoryId =null;
		try {
			cateGoryId = Integer.parseInt(json.getString(Blog.CATEGORYID));
		} catch (Exception e1) {
			e1.printStackTrace();
			cateGoryId=null;
		}
		String content;
		try {
			content = json.getString(BlogData.CONTENT);
		} catch (Exception e1) {
			return fault+"内容为空";
		}
		String     copyFrom    =   "";
		Date       createTime  =   DateUtil.toSqlDate();//
		String     blogDesc    =   "";
		Integer    dataId      =   null;//
		Integer    deleFlag    =   0;//
		Integer    userId      =   UserUtils.getUser().getUserId();//
		
		BlogData blogData  = new BlogData(content,copyFrom);//
		Blog     blog      = new Blog(title,userId, //
				 createTime, blogDesc,//
				 cateGoryId, dataId, deleFlag);//
		if(blogId==null){
			try {
				bolgService.insertBlogAndDate(blog, blogData);
			} catch (ServiceException e) {
				log.error(e);
				return e.getMessage();
			}
		}else{
			try {
				blog.setBlogId(blogId);
				blog.setUpdateTime( DateUtil.toSqlDate());
				bolgService.update(blog);
				if(content!=null){
					blogData.setDataId(blogId);
					blogDataService.update(blogData);
				}
			} catch (ServiceException e) {
				log.error(e);
				return e.getMessage();
			}
		}
		
		return success;
	}
	
	
	@ResponseBody
	@RequestMapping(value=AnRequest.A_BLOG_CATEGORY_MANAGE_QUERY,
	produces = "text/html;charset=UTF-8;")
	public String to_blog_category_manage_query(Integer rows,Integer page,HttpServletRequest request){
		
		QueryCondition queryCondition  = new QueryCondition(page,rows,request);//
		Page<BlogCategory>     pager   = bolgCategoryService.getPage(queryCondition);//
		
		JSONObject     json  		   = JSONObject.fromObject(pager);//
		return json.toString();
	}
	
	@RequestMapping(value=AnRequest.A_BLOG_QUERY_LIST)
	public ModelAndView to_blog_query_list(){
		return new ModelAndView(LocationConstants.A_BLOG_QUERY_LIST);
	}
	
	
	//3.to_blog_query
	@ResponseBody
	@RequestMapping(value=AnRequest.A_BLOG_QUERY,
			produces = "text/html;charset=UTF-8;")
	public String to_blog_query(Integer page,Integer rows,HttpServletRequest req){
		
		QueryCondition queryCondition  =   new QueryCondition(page,rows,req);//
		Map<String,Object> map         =   new HashMap<String,Object>();
			map.put(Blog.CREATEBY,         UserUtils.getUser().getUserId());
				queryCondition.setCondition(map);
		Page<Blog>     pager 		   =   bolgService.getPage(queryCondition  );//
		JSONObject     json  		   =   JSONutil.formatJsonBeanDate(pager);
		return json.toString();
	}
	
	@RequestMapping(value=AnRequest.A_BLOG_QUERY_VIEW)
	public ModelAndView to_blog_query_view(HttpServletRequest req){
		
		String blogId          =   req.getParameter(Blog.BLOGID);
		BlogData blogData      =   blogDataService.getBean(Integer.parseInt(blogId));
		
		return new ModelAndView(LocationConstants.A_BLOG_QUERY_VIEW)
		.addObject("blogData", blogData);
	}
	
	//4.do_blog_edit_update
	@RequestMapping(value=AnRequest.A_BLOG_EDIT_UPDATE+"/{blogId}",
			method=RequestMethod.GET)
	public ModelAndView do_blog_edit_update(@PathVariable Integer blogId){
		//根据 id查询内容、
		Blog blog         = bolgService.getBean(blogId);
		
		BlogData blogData = blogDataService.getBean(blogId);
		return new ModelAndView(LocationConstants.A_BLOG_EDIT)
		.addObject("blog",blog).addObject("blogData", blogData);
	}
	
	@ResponseBody
	@RequestMapping(value=AnRequest.A_BLOG_EDIT_DELETE,
			method=RequestMethod.POST,
			produces = "text/html;charset=UTF-8;")
	public String do_blog_edit_delete(HttpServletRequest request){
		List<Integer> ids = base_getIDS(request);
		
		//批量删除内容
		int result = deleteAll(ids);
			log.info("删除结果:"+result+"个 成功");

		return success+result;
	}
	
	private int deleteAll(List<Integer> ids) {
		
		return  bolgService.deleAll(ids);
	}
}
