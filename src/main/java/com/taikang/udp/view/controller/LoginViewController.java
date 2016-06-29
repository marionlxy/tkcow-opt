package com.taikang.udp.view.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.action.IUserAction;
import com.taikang.udp.sys.model.UserBO;

@Controller
public class LoginViewController {

	@Resource(name = ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;

	@Resource(name = IUserAction.ACTION_ID)
	private IUserAction userAction;

	/**
	 * 用户登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String optlogin(HttpServletRequest request, HttpServletResponse response, Model mod) {
		UserBO user = SecurityUserHolder.getCurrentUser();
		if (user != null && user.getId() > 0) {
			String url = WebUtil.getURL(request) + "/main";
			try {
				mod.addAttribute("loginMsg", "登录失败！");
				response.sendRedirect(url);
			} catch (IOException e) {
				return "login";
			}
		}
		mod.addAttribute("loginMsg", "用户名或密码错误！");
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
	 * 系统登出.
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBO user = SecurityUserHolder.getCurrentUser();
		if (user != null) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
					SecurityContextHolder.getContext().getAuthentication().getCredentials(),
					user.get_common_Authorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		String url = WebUtil.getURL(request) + "/login.htm";
		response.sendRedirect(url);
	}

	/**
	 * 主页面.
	 */
	// @RequestMapping("/main.htm")
	// public String mainPage(HttpServletRequest request, HttpServletResponse
	// response) {
	// return "main";
	// }
}
