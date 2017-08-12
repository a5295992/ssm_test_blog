package com.zking.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;

public class BaseController {

	
	/**
	 * 管理基础路径
	 */
	@Value("${admin.path}")
	protected String adminPath;
	
	protected String success = "0:";//成功
	protected String fault 	 = "1:";//失败
	protected String other   = "3:";//其他
	protected String unique  = "500:";//特殊
	
	
	public final String DO_BLOG_EDIT_ADD_AJAXDATA = "data";
	
	
	
	public List<Integer> base_getIDS(HttpServletRequest request){
		
		List<Integer> list = new ArrayList<Integer>();
		String data = request.getParameter(DO_BLOG_EDIT_ADD_AJAXDATA);
		if( data != null&&data.trim()!=""){
			JSONObject json = JSONObject.fromObject(data);
			
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String,Object>)json;
			for (Entry<String, Object> iterable_element : map.entrySet()) {
				list.add((Integer)iterable_element.getValue());
			}
		}
		
		return list;
	}
}
