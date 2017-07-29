package com.zking.controler;

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
}
