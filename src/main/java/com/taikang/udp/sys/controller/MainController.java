package com.taikang.udp.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.ILoginAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.vo.SysUserMenu;

@Controller("mainController")
@RequestMapping(value = "")
public class MainController extends BaseController {

	@Resource(name = ILoginAction.ACTION_ID)
	private ILoginAction loginAction;

	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		//LoginUser user = UserUtils.getUser();
		ModelAndView modelAndView = new ModelAndView();

		Dto param = new BaseDto();
		UserBO user = SecurityUserHolder.getCurrentUser();
		if (user == null || user.getId() <= 0) {
			String url = WebUtil.getURL(request) + "/login.htm";
			response.sendRedirect(url);
		}
		param.put("userId", user.getId());
		List<SysUserMenu> menuList = loginAction.queryMenuListByUser(param);

		List<SysUserMenu> parentList = new ArrayList<SysUserMenu>();
		List<SysUserMenu> childList = new ArrayList<SysUserMenu>();
		if (menuList != null) {
			for (SysUserMenu oneMenu : menuList) {
				if ("0".equals(String.valueOf(oneMenu.getParentId()))) {
					parentList.add(oneMenu);
				} else {
					String url = oneMenu.getUrl();
					String menuId = oneMenu.getMenuId();
					String reqUrl = url + "?reqMenuId=" + menuId;
					oneMenu.setUrl(reqUrl);
					childList.add(oneMenu);
				}
			}
		}

		modelAndView.addObject("operatorName", user.getUsername());
		modelAndView.addObject("childList", childList);
		modelAndView.addObject("parentList", parentList);

		modelAndView.addObject("content", "暂无内容");

		modelAndView.setViewName("main");

		return modelAndView;
	}
}
