package com.taikang.udp.common.modelview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.model.SysConfigBO;

public class JModelAndView extends ModelAndView{
	
	public JModelAndView(String viewName) {
		super.setViewName(viewName);
	}
	public JModelAndView(String viewName, SysConfigBO config,int type, HttpServletRequest request, HttpServletResponse response) {
		if (config.getSyslanguage() != null) {
			if (config.getSyslanguage().equals(Globals.DEFAULT_SYSTEM_LANGUAGE)) {
				if (type == Globals.SYSTEM_MANAGE_PAGE_SYS_TYPE) {
					super.setViewName(Globals.SYSTEM_MANAGE_PAGE_PATH + viewName);
				}
				else {
					super.setViewName(Globals.SYSTEM_FORNT_PAGE_PATH + viewName);
				}
			} else {
				if (type == Globals.SYSTEM_MANAGE_PAGE_SYS_TYPE) {
					super.setViewName(config.getSyslanguage() + "/system/" + viewName);
				}
				else{
					super.setViewName(config.getSyslanguage() + "/shop/" + viewName);
				}
			}
		} else {
			super.setViewName(viewName);
		}
		String webPath = WebUtil.getURL(request);
	
		super.addObject("domainPath", WebUtil.generic_domain(request));
		super.addObject("webPath", webPath);
		if (config.getImagewebserver() != null
				&& !config.getImagewebserver().equals("")) {
			super.addObject("imageWebServer", config.getImagewebserver());
		} else {
			super.addObject("imageWebServer", webPath);
		}
		
		super.addObject("config", config);
		super.addObject("user",SecurityUserHolder.getCurrentUser() );
		String query_url = "";
		if (request.getQueryString() != null
				&& !request.getQueryString().equals("")) {
			query_url = "?" + request.getQueryString();
		}
		super.addObject("current_url", request.getRequestURI() + query_url);
	}

}
