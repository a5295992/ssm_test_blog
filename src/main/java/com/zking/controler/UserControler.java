package com.zking.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.zking.commom.Page;
import com.zking.commom.QueryCondition;
import com.zking.commom.UserUtils;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.constants.PermissionAndRoleConstant;
import com.zking.entity.Permission;
import com.zking.entity.Role;
import com.zking.entity.User;
import com.zking.exception.ServiceException;
import com.zking.security.entity.ShiroUser;
import com.zking.service.PermissionService;
import com.zking.service.RoleService;
import com.zking.service.UserService;
import com.zking.service.impl.RoleServiceImpl;

/**
 * 用户 数据统一访问URL
 * 
 * @author Along
 * @Date 2017-7-30
 */
@Controller
@RequestMapping(value = AnRequest.A)
public class UserControler extends BaseController {
	private Logger log = Logger.getLogger(UserControler.class);

	@Autowired
	private UserService userService;// 用户service
	@Autowired
	private RoleService roleService;// 角色 service
	@Autowired
	private PermissionService permissionService; // 权限service

	private Integer page = 0;
	private Integer rows = 10;

	/**
	 * 欢迎页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/a/welecom")
	public ModelAndView welecome() {
		// 获取当前用户
		ShiroUser user = UserUtils.getUser();

		return new ModelAndView("welecom").addObject("user", user);
	}

	/**
	 * 获取 用户所有信息 GET方式 AOP 权限 检查 权限为 管理员 要求通过
	 * 
	 * @param queryCondition
	 * @return
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AUSERS, method = RequestMethod.GET)
	public ModelAndView getUsers() {

		return new ModelAndView(LocationConstants.AUSERS);
	}

	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AUSERSS, produces = "text/html;charset=UTF-8;")
	public String getUsers1(Integer page, Integer rows) {
		details(page, rows);
		page = this.page;
		rows = this.rows;
		// 分页插件 开始 是1
		QueryCondition queryCondition = new QueryCondition(page, rows);
		// 权限验证通过
		Page<User> users = userService.getPage(queryCondition);
		JSONObject json = JSONObject.fromObject(users);
		return json.toString();
	}

	/**
	 * 解决 分页错误
	 * 
	 * @param page
	 * @param rows
	 */
	private void details(Integer page, Integer rows) {
		if (page == null || page == 0) {
			this.page = 0;
		} else {
			this.page = page - 1;
		}
		if (rows == null) {
			this.rows = 10;
		} else {
			this.rows = rows;
		}
	}

	/** --------RoleManager--- 角色Start----##########################---- **/
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AROLES, method = RequestMethod.GET)
	public ModelAndView getRoles() {

		return new ModelAndView(LocationConstants.AROLES);
	}

	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AROLESS, produces = "text/html;charset=UTF-8;")
	public String getRoles(Integer page, Integer rows,HttpServletRequest  req) {
		details(page, rows);
		page = this.page;
		rows = this.rows;
		// 分页插件 开始 是1
		QueryCondition queryCondition = new QueryCondition(page, rows,req);
		// 权限验证通过
		RoleServiceImpl.boolean_getchildren = true;
		RoleServiceImpl.boolean_getmenus = true;
		Page<Role> roles = roleService.getPage(queryCondition);
		// 创建树 节点
		JSONObject json = JSONObject.fromObject(roles);
		return json.toString();
	}
	/**
	 * 用来获取角色列表
	 * @param page
	 * @param rows
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.AROLESS_LIST, produces = "text/html;charset=UTF-8;")
	public String getRolesList() {
		// 分页插件 开始 是1
		QueryCondition queryCondition = new QueryCondition(0, 20);
		// 权限验证通过
		RoleServiceImpl.boolean_getchildren = true;
		RoleServiceImpl.boolean_getmenus = true;
		Page<Role> roles = roleService.getPage(queryCondition);
		// 创建树 节点
		JSONArray json = JSONArray.fromObject(roles.getRows());
		return json.toString();
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.AADDROLE, produces = "text/html;charset=UTF-8;")
	public String addRole(HttpServletRequest req) {

		String roleName = WebUtils.getCleanParam(req, "roleName");
		String proleName = WebUtils.getCleanParam(req, "proleName");
		// 创建 role对象
		Role role = new Role();

		role.setRoleName(roleName);
		role.setDeleFlag(0);
		if (!StringUtils.isNullOrEmpty(proleName)) {
			role.setpRid(Integer.parseInt(proleName));
		}
		role.setpRid(Integer.parseInt(proleName));
		try {
			roleService.add(role);
		} catch (ServiceException e) {
			log.debug(e.getMessage());
			return e.getMessage();
		} catch (Exception e) {
			return fault + e.getMessage();
		}
		return success + "1";
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.AUPDATEROLE + "/{roleId}", produces = "text/html;charset=UTF-8;")
	public ModelAndView updateRole(@PathVariable Integer roleId) {
		Role role = roleService.getBean(roleId);

		QueryCondition queryCondition = new QueryCondition(0, 50);
		Page<Role> page = roleService.getPage(queryCondition);
		List<Role> prole = null;
		if (page != null) {
			prole = page.getRows();
		}
		return new ModelAndView(LocationConstants.AUPDATEROLE).addObject(
				"role", role).addObject("prole", prole);
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.AUPDATEROLEAJAX, produces = "text/html;charset=UTF-8;")
	public String updateRoleAjax(HttpServletRequest req) {
		Integer roleId = 0;
		String roleid = req.getParameter("roleId");
		if (!StringUtils.isNullOrEmpty(roleid)) {
			roleId = Integer.parseInt(roleid);
		}
		String roleName = WebUtils.getCleanParam(req, "roleName");

		String proleName = WebUtils.getCleanParam(req, "proleName");

		Role role = roleService.getBean(roleId);
		if (role != null) {

			if (!role.getRoleName().equals(roleName)) {
				role.setRoleName(roleName);
			}
			if (proleName != null && !role.getpRid().equals(proleName)) {
				role.setpRid(Integer.parseInt(proleName));
			}
		}
		log.info("更改后的role 为 " + role.getRoleName());
		try {
			roleService.update(role);
		} catch (ServiceException e) {
			return e.getMessage();
		}
		return success + "success";
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.ADELEROLE, produces = "text/html;charset=UTF-8;")
	public String deleRole(HttpServletRequest req) {
		String roleIds = req.getParameter("roleId");
		if (!StringUtils.isNullOrEmpty(roleIds)) {
			try {
				roleService.delete(Integer.parseInt(roleIds));
			} catch (ServiceException e) {
				return e.getMessage();
			}
			return success + "success";
		}
		return fault + "操作未成功";
	}

	/**
	 * --------PemissionManager--- 权限Start----##########################----/
	 * 
	 * @return
	 */
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.APERMISSIONS, method = RequestMethod.GET)
	public ModelAndView getPermissions() {

		return new ModelAndView(LocationConstants.APERMISSIONS);
	}

	@ResponseBody
	@RequiresRoles(value = PermissionAndRoleConstant.ADMIN)
	@RequestMapping(value = AnRequest.A_PERMISSION_SEARCH, produces = "text/html;charset=UTF-8;")
	public String getPermissions(Integer page, Integer rows,
			HttpServletRequest req) {
		// 分页
		details(page, rows);
		page = this.page;
		rows = this.rows;
		// 分页插件 开始 是1
		QueryCondition queryCondition = new QueryCondition(page, rows, req);
		// 权限验证通过
		Page<Permission> permissions = permissionService
				.getPage(queryCondition);
		JSONObject json = JSONObject.fromObject(permissions);
		return json.toString();
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.A_PERMISSION_ADD, produces = "text/html;charset=UTF-8;")
	public String addPermission(Permission permission) {
		permission.setDeleFlag(0);
		try {
			permissionService.add(permission);
		} catch (ServiceException e) {
			log.error(e);
			return e.getMessage();
		}

		return success + "操作成功";
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.A_PERMISSION_UPDATE_VIEW, produces = "text/html;charset=UTF-8;")
	public String update_view(HttpServletRequest req) {

		String perIds = req.getParameter("perId");
		if (!StringUtils.isNullOrEmpty(perIds)) {
			Permission permission = permissionService.getBean(Integer
					.parseInt(perIds));
			JSONObject json = JSONObject.fromObject(permission);
			return json.toString();
		}
		return "";
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.A_PERMISSION_UPDATE, produces = "text/html;charset=UTF-8;")
	public String permission_update(HttpServletRequest req) {
		// 要修改此项
		String perIds = req.getParameter("perId");
		String perName = req.getParameter("perName");
		String perMs = req.getParameter("perMs");
		String roleId = req.getParameter("roleId");
		
		if (!StringUtils.isNullOrEmpty(perIds)) {
			Permission permission = permissionService.getBean(Integer
					.parseInt(perIds));

			permission.setPerMs(perMs);
			if (!StringUtils.isNullOrEmpty(perName)) {
				permission.setPerName(perName);
			} else {
				return fault + "权限名不能为空";
			}

			if (!StringUtils.isNullOrEmpty(roleId)) {
				permission.setRoleId(Integer.parseInt(roleId));
			} else {
				return fault + "角色名不能为空";
			}
			try {
				permissionService.update(permission);
			} catch (ServiceException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		} else {
			return fault + "未选中内容";
		}
		return success;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = AnRequest.A_PERMISSION_DELETE, 
	produces = "text/html;charset=UTF-8;")
	public String permission_delete(HttpServletRequest req) {
		
		String data = req.getParameter("data");
		JSONObject json = JSONObject.fromObject(data);
		List<Integer> ids = new ArrayList<Integer>();
		
		Map<String,Integer> map = (Map<String,Integer>)json;
		for (Entry<String, Integer> integer : map.entrySet()) {
			ids.add(integer.getValue());
		}
		
		int result = permissionService.deleAll(ids);
		
		return success+result;
	}
}
