package com.taikang.tkcoww.orderVisit.controller;

import com.taikang.tkcoww.orderVisit.action.IOrderVisitAction;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;

import java.util.HashMap;

import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;

import javax.annotation.Resource;

import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;


/**
  * OrderVisitController
  */
@Controller("orderVisitController")
@RequestMapping(value="/orderVisit")
public class OrderVisitController  extends BaseController  {
		
	@Resource(name=IOrderVisitAction.ACTION_ID)
	private IOrderVisitAction orderVisitAction;
		
	/**
	 * 打开主查询页面
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showOrderVisitIndexPage() {
		return "ordervisit/ordervisitIndex"; 
	}
	
	/**
	 * 查询信息列表
	 * @return 分页列表数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> getOrderVisitList(HttpServletRequest request,CurrentPage page){
		Map<String, Object> map = new HashMap<String, Object>();
		
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = orderVisitAction.getOrderVisitList(page);
		
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		
		return map;
	}

	/**
	 * 打开新增或修改页面
	 * @return
	 */
	@RequestMapping("edit")
	public String showOrderVisitEditPage(String visitId,Model model) {
		
		if(visitId!=null && !visitId.equals(""))
		{
			model.addAttribute("visitId",visitId);
		}
		
		return "ordervisit/ordervisitEdit"; 
	}
	
	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * @return
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	public Dto getOrderVisitById(@RequestParam("visitId")String visitId)
	{
		Dto param = new BaseDto();
		param.put("visitId", visitId);
		return orderVisitAction.findOne(param);
	}
	
	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	private Map<String,String> saveOrderVisitInfo(HttpServletRequest request)
	{
		Map<String,String> map=new HashMap<String,String>();
		
		Dto param = getParamAsDto(request);
		if(param.get("visitId") ==null ||"".equals(param.get("visitId")))
		{
			orderVisitAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		}
		else
		{
			orderVisitAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");
		
		return map;
	}
	
	/**
	*删除一条或多条记录
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String, String> deleteOrderVisit(@RequestParam("visitId") String visitId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		Dto user = SecurityUserHolder.getCurrentUser().toDto();
	
		param.put("visitId", visitId);
		param.put("modifiedTime", TKDateTimeUtils.getTodayTimeStamp());
		param.put("modifiedBy", user.getAsString("id"));
		param.put("delflag", "1");
		orderVisitAction.updateObject(param);
		
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");
		
		return map;
	}
}

