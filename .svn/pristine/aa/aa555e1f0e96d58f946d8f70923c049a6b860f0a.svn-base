package com.taikang.udp.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taikang.udp.framework.core.web.BaseController;

@Controller("commonController")
@RequestMapping(value = "")
public class CommonController extends BaseController {

	@Autowired
	HttpServletRequest request;

	@RequestMapping("/common/error/app-error")
	public String appErrorPage() {
		return "common/error/app-error";
	}

	@RequestMapping("/common/error/500")
	public String to500Page() {
		return "common/error/500";
	}

	@RequestMapping("/common/error/404")
	public String to404Page() {
		return "common/error/404";
	}

	@RequestMapping("/common/error/403")
	public String to403Page() {
		return "common/error/403";
	}
}
