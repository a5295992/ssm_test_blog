package com.zking.controler;

import org.springframework.beans.factory.annotation.Value;

public class BaseController {

	
	/**
	 * 管理基础路径
	 */
	@Value("${admin.path}")
	protected String adminPath;
}
