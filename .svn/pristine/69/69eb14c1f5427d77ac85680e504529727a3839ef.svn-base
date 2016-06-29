package com.taikang.udp.view.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.action.IUserAction;
import com.taikang.udp.sys.model.UserBO;

@Controller
public class LoginViewController {
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	@Resource(name=IUserAction.ACTION_ID)
	private IUserAction userAction;
	
	
	/**
	 * 用户登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String optlogin(HttpServletRequest request,
			HttpServletResponse response, String url) {
		return "login";
	}
	
	/**
	 * 登陆成功跳转.
	 */
	@RequestMapping("/login_success.htm")
	public void login_success(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBO user = SecurityUserHolder.getCurrentUser();
		HttpSession session = request.getSession(false);
		session.setAttribute("user", user);
		session.setAttribute("lastLoginDate", new Date());// 设置登录时间
		session.setAttribute("loginIp", WebUtil.getIpAddr(request));// 设置登录IP
		session.setAttribute("login", true);// 设置登录标识
		String url = WebUtil.getURL(request) + "/main";
		response.sendRedirect(url);
	}

	/**
	 * 主页面.
	 */
	//@RequestMapping("/main.htm")
	//public String mainPage(HttpServletRequest request, HttpServletResponse response) {
	//	return "main";
	//}
}
