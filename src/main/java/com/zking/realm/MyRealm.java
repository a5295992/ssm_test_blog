package com.zking.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zking.dao.PermissionMapper;
import com.zking.dao.RoleMapper;
import com.zking.dao.UserMapper;
import com.zking.entity.Permission;
import com.zking.entity.PermissionExample;
import com.zking.entity.Role;
import com.zking.entity.RoleExample;
import com.zking.entity.User;
import com.zking.entity.UserExample;
import com.zking.exception.AuthFailException;
import com.zking.security.entity.ShiroUser;
/**
 * 
 * @author Administrator
 * doc:
 */
@Component
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper pMapper;
	
	private static Logger log = Logger.getLogger(MyRealm.class);
	/**
	 * 权限验证
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
		// 根据 用户名获取所有角色
		Set<String> roles = getAllRoles(user);
		//根据 用户角色ID 获取  所有权限
		Set<String> permissions = getPermissions(user);

		SimpleAuthorizationInfo infor = new SimpleAuthorizationInfo(roles);
		infor.addStringPermissions(permissions);
		return infor;
	}
	
	private Set<String> getPermissions(ShiroUser user) {
		Set<String> permissions = new HashSet<String>();

		for (Role r : getRolesByPid(user.getRoleId())) {
			for (Permission per : getAllPermissionsByRoleId(r.getRoleId())) {
				permissions.add(per.getPerName());
			}
		}
		return permissions;
	}

	private List<Permission> getAllPermissionsByRoleId(Integer roleId) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return pMapper.selectByExample(example);

	}

	private List<Role> getRolesByPid(Integer id) {
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andPRidEqualTo(id);
		return roleMapper.selectByExample(roleExample);
	}

	private Set<String> getAllRoles(ShiroUser user) {
		Set<String> roles = new HashSet<String>();
		// 1.获取 当前 用户角色
		Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
		roles.add(role.getRoleName());

		// 2.获取角色下级 角色

		return roles;
	}
	/**
	 * 登录|验证 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken token_ = (UsernamePasswordToken) token;
		String userName = token_.getUsername();
		String password = new String(token_.getPassword());
		// 比对 数据库中的信息 与 令牌中的信息是否一致
		User user = passwordAndUserNameIsRight(userName, password);
		// 创建 身份信息
		SimpleAuthenticationInfo infor = createAuthInfor(token_, user);
		return infor;

	}

	private User passwordAndUserNameIsRight(String userName, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNameEqualTo(userName)
				.andPasswordEqualTo(password);

		List<User> users = userMapper.selectByExample(userExample);
		User user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}
	
	private SimpleAuthenticationInfo createAuthInfor(
			UsernamePasswordToken token_, User user) {
		SimpleAuthenticationInfo infor = null;
		if (user != null) {
			String host = token_.getHost();
			infor = new SimpleAuthenticationInfo(new ShiroUser(
					user.getUserName(), user.getUserCname(), user.getUserId(),
					host, user.getRoleId()), user.getPassword(), getName());
		} else {
			throw new AuthFailException("用户或密码错误");
		}
		return infor;
	}
}