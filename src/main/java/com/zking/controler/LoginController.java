package com.zking.controler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zking.commom.UserUtils;
import com.zking.commom.ValidateCode;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.exception.AuthFailException;
import com.zking.security.SessionDao;
import com.zking.security.entity.ShiroUser;
import com.zking.service.LoginService;

@Controller
public class LoginController extends BaseController {
	private Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	@Autowired
	private SessionDao sessionDAO;

	/**
	 * 前台登录 /login
	 * 
	 * @return views/ ..jsp
	 */
	@RequestMapping(value = AnRequest.LOGIN, method = RequestMethod.GET)
	public String toLogin() {
		return LocationConstants.LOGIN;
	}

	/**
	 * 后台登录 a/login
	 * 
	 * @return views/ ..jsp
	 */
	
	@RequestMapping(value = AnRequest.ALOGIN)
	public String toALogin() {
		ShiroUser shiroUser = UserUtils.getUser();

		if (log.isDebugEnabled() && shiroUser != null) {
			log.debug("当前 活跃的 sessio 大小为"
					+ sessionDAO.getActiveSessions().size());
			log.debug("当前 活跃的 sessio 名"
					+ sessionDAO.getActiveSessionsCacheName());
			log.debug("当前 登录用户 :" + shiroUser.getUserCname());
		}
		// 如果 已经登录 再进入登录页 则退出原账号
		String location = "";
		if (shiroUser != null) {
			location = "redirect:"+AnRequest.AINDEX;
		}else {
			// 未登录
			location = LocationConstants.ALOGIN;
		}
		return location;
	}

	/**
	 * 提交表单登录 AJAX 方式 POST 创建资源
	 * 
	 * @param userName
	 *            用户| password 密码|validate 验证码
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = AnRequest.AJAXLOGIN, produces = "text/html;charset=UTF-8;")
	public String logins(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "validate") String validate,
			HttpServletRequest request) {
		// 验证验证码
		if (validata(validate, request)) {
			ShiroUser shiroUser = UserUtils.getUser();// 获取当前用户
			if (shiroUser != null) {
				return "3:" + "您已使用 :" +shiroUser.getUserName()+ "在线！";
			}
			// 如果 当前用户 获取为空 进行登录操作
			try {
				loginService.login(userName, password, request);
			} catch (AuthFailException e) {
				return fault + e.getMessage();
			}
		} else {
			return fault + "验证码错误";
		}

		return success;
	}

	/**
	 * 验证码 验证
	 * 
	 * @param validate
	 * @return
	 */
	private boolean validata(String validate, HttpServletRequest request) {

		/*String code = (String) request.getSession().getAttribute("code");
		return code.toLowerCase().equals(validate);*/
		return true;
	}

	/**
	 * 退出操作
	 * 统一退出操作 入口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = AnRequest.LOGINOUT, method = RequestMethod.GET,
			produces = "text/html;charset=UTF-8;")
	public String logOut() {
		//获取当前 用户信息
		ShiroUser shiroUser  = UserUtils.getUser();
		//如果 当前用户不为空 则执行 操作
		if(shiroUser != null){
			//清除登录信息
			//如果有缓存 则 移除缓存 
			//清空 会话内容
			loginService.logout(SecurityUtils.getSubject());
		}else {
			//否则 ： 本次请求无效
			return "3:您未登录 或该账号已经退出";
		}
		
		return "0:操作成功[退出账号 "+shiroUser.getUserName()+"]";
	}
	/**
	 * 验证码生成器
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getValidateImage")
	public void validateImage(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = req.getSession();
		ValidateCode vCode = new ValidateCode(120, 40, 5, 100);
		session.setAttribute("code", vCode.getCode());
		OutputStream os = response.getOutputStream();
		vCode.write(os);
		os.close();
	}
	
	/**
	 * 进入 后台主页 入口
	 * @return 后台主页
	 */
	@RequestMapping(value=AnRequest.AINDEX)
	public ModelAndView toAIndex(){
		
		
		return new ModelAndView(LocationConstants.AINDEX);
	}
}
