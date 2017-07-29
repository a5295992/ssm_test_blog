package com.zking.controler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zking.commom.UserUtils;
import com.zking.commom.ValidateCode;
import com.zking.constants.AnRequest;
import com.zking.constants.LocationConstants;
import com.zking.exception.AuthFailException;
import com.zking.security.SessionDao;
import com.zking.security.entity.ShiroUser;
import com.zking.service.LoginService;

@Controller
public class LoginControler extends BaseController {
	private Logger log = Logger.getLogger(LoginControler.class);

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
	@RequestMapping(value = AnRequest.ALOGIN, method = RequestMethod.GET)
	public String toALogin() {
		ShiroUser shiroUser = UserUtils.getUser();

		if (log.isDebugEnabled()&& shiroUser !=null) {
			log.debug("当前 活跃的 sessio 大小为"
					+ sessionDAO.getActiveSessions().size());
			log.debug("当前 活跃的 sessio 名"
					+ sessionDAO.getActiveSessionsCacheName());
			log.debug("当前 登录用户 :" + shiroUser.getUserCname());
		}
		// 如果 已经登录 再进入登录页 则退出原账号
		String location = "";
		if (shiroUser != null) {
			location = LocationConstants.AINDEX;// 登录首页
		}
		// 未登录
		location = LocationConstants.ALOGIN;

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
	@RequestMapping(value = AnRequest.AJAXLOGIN, method = RequestMethod.POST)
	public String login(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "validate") String validate,
			HttpServletRequest request) {
		String stauts = "";
		// 验证验证码
		if (validata(validate, request)) {
			ShiroUser shiroUser = UserUtils.getUser();// 获取当前用户
			if (shiroUser != null) {
				return "redirect:" + adminPath;
			}
			// 如果 当前用户 获取为空 进行登录操作
			try {
				loginService.login(userName, password, request);
			} catch (AuthFailException e) {
				stauts = e.getMessage();
			}
		} else {
			stauts = "msg:验证码错误";
		}
		return stauts;
	}

	/**
	 * 验证码 验证
	 * 
	 * @param validate
	 * @return
	 */
	private boolean validata(String validate, HttpServletRequest request) {

		String code = (String) request.getSession().getAttribute("code");
		return code.equals(validate);
	}

	/**
	 * 退出操作
	 * 
	 * @return
	 */
	@RequestMapping(value = AnRequest.AJAXLOGIN, method = RequestMethod.GET)
	public String logOut() {

		return "";
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
}
