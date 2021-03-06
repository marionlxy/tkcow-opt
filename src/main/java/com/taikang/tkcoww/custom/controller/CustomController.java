package com.taikang.tkcoww.custom.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.IUserAction;
import com.taikang.udp.sys.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * CustomController
 */
@Controller("customController")
@RequestMapping(value = "custom")
public class CustomController extends BaseController {
	@Resource(name = IUserAction.ACTION_ID)
	private IUserAction userAction;
	
	/**
	 * 打开主页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("showCutomer")
	public String showCutomer() {
		return "custom/userIndex";
	}
	
	/**
	 * 打开添加主页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("addCutomer")
	public String addCutomer(HttpServletRequest request, Model mod) {
		return "custom/userAdd";
	}
	
	/**
	 * 打开添加主页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("editCutomer")
	public String editCutomer(HttpServletRequest request, Model mod) {
		Dto param = CommonUtil.getParamAsDto(request);
		Dto user = userAction.findOne(param);
		mod.addAttribute("username", user.getAsString("username"));
		mod.addAttribute("truename", user.getAsString("truename"));
		mod.addAttribute("telephone", user.getAsString("telephone"));
		mod.addAttribute("email", user.getAsString("email"));
		mod.addAttribute("address", user.getAsString("address"));
		mod.addAttribute("addtime", user.getAsString("addtime"));
		mod.addAttribute("id", user.getAsString("id"));
		mod.addAttribute("msn", user.getAsString("msn"));
		return "custom/userEdit";
	}
	
	/**
	 * 查询客户列表
	 * 
	 * @return 页面地址
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getCutomerList")
	@ResponseBody
	public Map<String,Object> getCutomer(HttpServletRequest request,CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("userrole", "0");
		param.put("deletestatus", "0");
		page.setParamObject(param);
		CurrentPage currentPage = userAction.queryForPage(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}
	
	/**
	 * 查询客户列表
	 * 
	 * @return 页面地址
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("searchCutomerList")
	@ResponseBody
	public Map<String,Object> searchCutomerList(HttpServletRequest request,CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("userrole", "0");
		page.setParamObject(param);
		CurrentPage currentPage = userAction.searchForPage(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}
	
	/**
	 * 添加客户
	 * 
	 * @return 页面地址
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("saveCustomInfo")
	@ResponseBody
	public ResponseEntity<String> saveCustomInfo(HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("userrole", "0");
		param.put("deletestatus", 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(param.getAsString("addtime"));
			param.remove("addtime");
			param.put("addtime", date);
			param.put("gold", "0");
			param.put("integral", "0");
			param.put("sex", "3");
			param.put("userCredit", "0");
			userAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
			map.put(RTN_RESULT, "true");
		} catch (Exception e) {
			map.put(MESSAGE_INFO, "新增失败！");
			map.put(RTN_RESULT, "false");
		}
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}
	
	/**
	 * 添加客户
	 * 
	 * @return 页面地址
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("updataCustomInfo")
	@ResponseBody
	public ResponseEntity<String> updataCustomInfo(HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto param = CommonUtil.getParamAsDto(request);
		try {
			Date date = new Date();
			param.put("modifytime", date);
			userAction.updateObject(param);
			map.put(MESSAGE_INFO, "编辑成功！");
			map.put(RTN_RESULT, "true");
		} catch (Exception e) {
			map.put(MESSAGE_INFO, "编辑失败！");
			map.put(RTN_RESULT, "false");
		}
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}
	
	/**
	 * 添加客户
	 * 
	 * @return 页面地址
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("delCustomInfo")
	@ResponseBody
	public ResponseEntity<String> delCustomInfo(HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto param = CommonUtil.getParamAsDto(request);
		map.put("tabName", param.getAsString("tabName"));
		param.put("deletestatus", 1);
		try {
			Date date = new Date();
			param.put("modifytime", date);
			userAction.updateObject(param);
			map.put(RTN_RESULT, "true");
		} catch (Exception e) {
			map.put(RTN_RESULT, "false");
		}
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}
}
