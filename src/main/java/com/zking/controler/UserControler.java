package com.zking.controler;

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
import com.zking.commom.UserUtils;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.constants.PermissionAndRoleConstant;
import com.zking.entity.Role;
import com.zking.entity.User;
import com.zking.security.entity.ShiroUser;
import com.zking.service.PermissionService;
import com.zking.service.RoleService;
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
	private Logger log = Logger.getLogger(LoginControler.class);
	
	@Autowired
	private UserService userService;// 用户service
	@Autowired
	private RoleService roleService;//角色 service
	@Autowired
	private PermissionService permissionService; //权限service
	/**
	 *  欢迎页面
	 * @return
	 */
	@RequestMapping(value="/a/welecom")
	public ModelAndView welecome(){
		//获取当前用户
		ShiroUser user = UserUtils.getUser();
		
		return new ModelAndView("welecom").addObject("user",user);
	}
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
	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AUSERSS,produces = "text/html;charset=UTF-8;")
	public String getUsers1(Integer page,Integer rows) {
		page =page -1;
		log.debug("pages "+page+"hangshu " +rows);
		//分页插件 开始 是1
		QueryCondition queryCondition =new QueryCondition(page,rows);
		// 权限验证通过
		Page<User> users = userService.getPage(queryCondition );
		JSONArray json = JSONArray.fromObject(users.getList());
		log.debug(json);
		return json.toString();
	}
	/**
	 *  角色列表
	 * @return
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AROLES, method = RequestMethod.GET)
	public ModelAndView getRoles(){
		
		return new ModelAndView(LocationConstants.AROLES);
	}
	/**
	 * 角色列表 数据
	 * @param page 当前页码
	 * @param rows 分页行数
	 * @return
	 */
	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AROLES)
	public String getRoles(Integer page,Integer rows){
		page =page -1;
		log.debug("pages "+page+"hangshu " +rows);
		//分页插件 开始 是1
		QueryCondition queryCondition =new QueryCondition(page,rows);
		// 权限验证通过
		Page<Role> roles = roleService.getPage(queryCondition );
		JSONArray json = JSONArray.fromObject(roles.getList());
		log.debug(json);
		return json.toString();
	}
	/**
	 * 
	 * @return
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.APERMISSIONS, method = RequestMethod.GET)
	public ModelAndView getPermissions(){
		
		return new ModelAndView(LocationConstants.APERMISSIONS);
	}
	/**
	 * 权限 数据
	 * @param page 页码
	 * @param rows 分页行数
	 * @return
	 */
	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.APERMISSIONSS)
	public String getPermissions(Integer page,Integer rows){
		page =page -1;
		log.debug("pages "+page+"hangshu " +rows);
		//分页插件 开始 是1
		QueryCondition queryCondition =new QueryCondition(page,rows);
		// 权限验证通过
		Page<Role> roles = roleService.getPage(queryCondition );
		JSONArray json = JSONArray.fromObject(roles.getList());
		log.debug(json);
		return json.toString();
	}
}
