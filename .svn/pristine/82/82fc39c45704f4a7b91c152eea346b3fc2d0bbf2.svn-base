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
import com.taikang.udp.sys.action.ISysParamAction;

/**
 * SysParamController
 */
@Controller("sysParamController")
@RequestMapping(value = "/sysParam")
public class SysParamController extends BaseController {

	@Resource(name = ISysParamAction.ACTION_ID)
	private ISysParamAction sysParamAction;

	/**
	 * 打开主查询页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("")
	public String showSysParamIndexPage() {
		return "sysParam/sysParamIndex";
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getSysParamList")
	@ResponseBody
	public Map<String, Object> getSysParamList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = sysParamAction.queryForPage(page);
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
	@RequestMapping("/showSysParamEditPage")
	public String showSysParamEditPage(String rowId, Model model) {
		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}
		return "sysParam/sysParamEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param rowId
	 * @return Dto
	 */
	@RequestMapping("/getSysParamById")
	@ResponseBody
	public Dto getSysParamById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		return sysParamAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/saveSysParamInfo")
	@ResponseBody
	private Map<String, String> saveSysParamInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			sysParamAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			sysParamAction.updateObject(param);
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
	@RequestMapping(value = "/deleteSysParam")
	@ResponseBody
	public Map<String, String> deleteSysParam(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		sysParamAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
}
