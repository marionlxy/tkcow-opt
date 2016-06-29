package com.taikang.udp.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.ILogAction;

/**
 * 
 * 日志信息维护<br/>
 * 
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年1月21日 下午3:37:23
 */
@Controller("logController")
@RequestMapping(value = "/log")
public class LogController extends BaseController {

	@Resource(name = ILogAction.ACTION_ID)
	private ILogAction logAction;

	/**
	 * 打开主查询页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showLogIndexPage() {
		return "/sys/log/logIndex";
	}

	/**
	 * 按条件查询日志信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getBycomLogList")
	@ResponseBody
	public Map<String, Object> getBycomLogList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = logAction.queryByconForPage(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开详细信息展示页面<br/>
	 * 
	 * @param rowId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showDetailPage")
	public String showDetailPage(String rowId, Model model) {
		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}
		return "/sys/log/logShow";
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getLogList")
	@ResponseBody
	public Map<String, Object> getLogList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = logAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面<br/>
	 * 
	 * @param rowId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showLogEditPage")
	public String showLogEditPage(String rowId, Model model) {

		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}

		return "/sys/log/logEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param rowId
	 * @return Dto
	 */
	@RequestMapping("/getLogById")
	@ResponseBody
	public Dto getLogById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("logId", rowId);
		return logAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveLogInfo")
	@ResponseBody
	private Map<String, String> saveLogInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			logAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			logAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param rowId
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteLog")
	@ResponseBody
	public Map<String, String> deleteLog(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		logAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
}
