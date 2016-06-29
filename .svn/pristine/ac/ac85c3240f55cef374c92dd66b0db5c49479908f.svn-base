package com.taikang.udp.manage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.model.UserBO;

@Controller
public class BaseManageController {

	@Resource(name = ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;

	@RequestMapping("/login_error.htm")
	public ModelAndView login_error(HttpServletRequest request, HttpServletResponse response) {
		String login_role = (String) request.getSession(false).getAttribute("login_role");
		ModelAndView mv = null;
		if (login_role == null)
			login_role = "user";
		if (login_role.equals("admin")) {
			mv = new JModelAndView("admin/blue/login_error", configAction.getSysConfig(), 0, request, response);
		} else {
			mv = new JModelAndView("error", configAction.getSysConfig(), 1, request, response);
			mv.addObject("url", WebUtil.getURL(request) + "/user/login.htm");
		}
		mv.addObject("op_title", "登录失败");
		return mv;
	}

	//@RequestMapping("/login_success.htm")
	public void login_success(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBO user = SecurityUserHolder.getCurrentUser();
		HttpSession session = request.getSession(false);
		session.setAttribute("user", user);
		session.setAttribute("lastLoginDate", new Date());// 设置登录时间
		session.setAttribute("loginIp", WebUtil.getIpAddr(request));// 设置登录IP
		session.setAttribute("login", true);// 设置登录标识
		String role = user.getUserrole();
		String url = WebUtil.getURL(request) + "/user_login_success.htm";
		String login_role = (String) session.getAttribute("login_role");

		if (login_role.equals("admin")) {
			if (role.indexOf("ADMIN") >= 0) {
				url = WebUtil.getURL(request) + "/admin/index.htm";
				request.getSession(false).setAttribute("admin_login", true);
			}
		}
		response.sendRedirect(url);
	}

	@RequestMapping("/admin/index.htm")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new JModelAndView("admin/blue/manage", configAction.getSysConfig(), 0, request, response);
		return mv;
	}

	/**
	 * 登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/login.htm")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new JModelAndView("admin/blue/login", configAction.getSysConfig(), 0, request, response);
		UserBO user = SecurityUserHolder.getCurrentUser();
		if (user != null) {
			mv.addObject("user", user);
		}
		return mv;
	}

	@RequestMapping("/admin/welcome.htm")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new JModelAndView("admin/blue/welcome", configAction.getSysConfig(), 0, request, response);
		Properties props = System.getProperties();
		mv.addObject("os", props.getProperty("os.name"));
		mv.addObject("java_version", props.getProperty("java.version"));
		mv.addObject("shop_version", Globals.DEFAULT_SHOP_VERSION);
		mv.addObject("web_server_version", request.getSession(false).getServletContext().getServerInfo());
		return mv;
	}

	/**
	 * 管理员退出，清除管理员权限数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/logout.htm")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		UserBO user = SecurityUserHolder.getCurrentUser();
		if (user != null) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
					SecurityContextHolder.getContext().getAuthentication().getCredentials(),
					user.get_common_Authorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		return new ModelAndView("redirect:/index.htm");
	}
}
