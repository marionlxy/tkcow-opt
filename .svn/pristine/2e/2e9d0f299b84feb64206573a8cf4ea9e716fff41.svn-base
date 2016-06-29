package com.taikang.udp.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IUserRoleAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;

/**
 * UserRoleController
 */
@Controller("userRoleController")
@RequestMapping(value = "/userRole")
public class UserRoleController extends BaseController {

	@Resource(name = IUserRoleAction.ACTION_ID)
	private IUserRoleAction userRoleAction;

	/**
	 * 打开主查询页面<br/>
	 * 
	 * @return String 页面地址
	 */
	@RequestMapping("")
	public String showUserRoleIndexPage() {
		return "userRole/userRoleIndex";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getUserRoleList")
	@ResponseBody
	public Map<String, Object> getUserRoleList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = userRoleAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/showUserRoleEditPage")
	public String showUserRoleEditPage(String rowId, Model model) {
		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}
		return "userRole/userRoleEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/getUserRoleById")
	@ResponseBody
	public Dto getUserRoleById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		return userRoleAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/saveUserRoleInfo")
	@ResponseBody
	private Map<String, String> saveUserRoleInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			userRoleAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			userRoleAction.updateObject(param);
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
	@RequestMapping(value = "/deleteUserRole")
	@ResponseBody
	public Map<String, String> deleteUserRole(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		userRoleAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 批量保存用戶角色信息<br/>
	 * 
	 * @param roleMenus
	 * @param userCode
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveUserRoles")
	@ResponseBody
	public Map<String, String> saveUserRoles(HttpServletRequest request, @RequestBody List<Map> roleMenus,
			@RequestParam("userCode") String userCode) {
		Map<String, String> map = new HashMap<String, String>();
		UserBO user = SecurityUserHolder.getCurrentUser();
		//LoginUser user = UserUtils.getUser();
		String loginId = String.valueOf(user.getId());

		Dto reqDto = getParamAsDto(request);
		reqDto.put("roleMenus", roleMenus);
		reqDto.put("userCode", userCode);
		reqDto.put("loginId", loginId);
		// 批量保存用户角色信息
		userRoleAction.batchSaveUserRole(reqDto);
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");
		return map;
	}
}
