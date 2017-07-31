package com.zking.controler;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.constants.PermissionAndRoleConstant;
import com.zking.entity.User;
import com.zking.service.UserService;

/**
 * 用户 数据统一访问URL
 * 
 * @author Along
 * @Date 2017-7-30
 */
@Controller
@RequestMapping(value = AnRequest.A)
public class UserControler {

	@Autowired
	private UserService userService;// 用户service
	private Logger log = Logger.getLogger(LoginControler.class);

	/**
	 * 获取 用户所有信息 GET方式
	 * AOP 权限 检查  权限为 管理员 要求通过
	 * @param queryCondition
	 * @return
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AUSERS, method = RequestMethod.GET)
	public ModelAndView getUsers() {
		
		return new ModelAndView(LocationConstants.AUSERS);
	}

	/**
	 * 用户 POST 请求 从表单传来查询条件 返回 相应数据
	 * 
	 * @param queryCondition
	 *            自定义 查询条件对象
	 * @return 返回 相应数据和页面
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@ResponseBody
	@RequestMapping(value = AnRequest.AUSERSS, method = RequestMethod.GET)
	public String getUsers1(Integer page,Integer rows) {
		QueryCondition queryCondition =new QueryCondition(page,rows);
		// 权限验证通过
		Page<User> users = userService.getPage(queryCondition );
		JSONArray json = JSONArray.fromObject(users.getList());
		log.debug(json);
		return json.toString();
	}
}
