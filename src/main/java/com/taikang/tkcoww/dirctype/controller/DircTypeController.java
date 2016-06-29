package com.taikang.tkcoww.dirctype.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.IDictEntryAction;
import com.taikang.udp.sys.util.CommonUtil;

@Controller("dictype")
@RequestMapping(value = "/dictype")
public class DircTypeController extends BaseController {
	
	@Resource(name = IDictEntryAction.ACTION_ID)
	private IDictEntryAction dictEntryAction;
	
	@RequestMapping("/askFor")
	public String askForIndexPage(Model model) {
		model.addAttribute("dicType", "means_name");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/askForMethod")
	public String askForMethodIndexPage(Model model) {
		model.addAttribute("dicType", "means_method");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/tiyan")
	public String tiyanIndexPage(Model model) {
		model.addAttribute("dicType", "tiyan");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/age")
	public String ageIndexPage(Model model) {
		model.addAttribute("dicType", "nianl");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/time")
	public String timeIndexPage(Model model) {
		model.addAttribute("dicType", "timeYY");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/server")
	public String serverIndexPage(Model model) {
		model.addAttribute("dicType", "service");
		return "dircType/dircTypeIndex";
	}
	
	@RequestMapping("/addNew")
	public String addNewPage(HttpServletRequest request, Model model) {
		Dto param = CommonUtil.getParamAsDto(request);
		String type = param.getAsString("type");
		model.addAttribute("dictTypeId", type);
		return "dircType/dircTypeAdd";
	}
	
	@RequestMapping("/edit")
	public String editPage(HttpServletRequest request, Model model) {
		Dto param = CommonUtil.getParamAsDto(request);
		Dto result = dictEntryAction.findOne(param);
		model.addAttribute("data", result);
		return "dircType/dircTypeEdit";
	}
}
