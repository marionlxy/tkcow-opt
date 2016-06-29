package com.taikang.udp.sys.controller;

import java.util.HashMap;
import java.util.List;
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
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IRoleAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;

/**
 * RoleController
 */
@Controller("roleController")
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

	@Resource(name = IRoleAction.ACTION_ID)
	private IRoleAction roleAction;

	/**
	 * 打开主查询页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("")
	public String showRoleIndexPage() {
		return "sys/role/roleIndex";
	}

	/**
	 * 
	 * 查询指定用户是否拥有角色信息列表<br/>
	 * 
	 * @param request
	 * @param userCode
	 * @return Map<String,Object> 分页列表信息
	 */
	@RequestMapping("/getUsersRoleList")
	@ResponseBody
	public Map<String, Object> getUsersRoleList(HttpServletRequest request, @RequestParam("userCode") String userCode) {
		Map<String, Object> map = new HashMap<String, Object>();

		Dto param = getParamAsDto(request);
		param.put("userCode", userCode);
		List<Dto> list = roleAction.getUsersRoleList(param);
		map.put("rows", list);

		return map;
	}

	/**
	 * 按条件分页查询列表信息<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	public Map<String, Object> getRoleList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = roleAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增新增页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("/showRoleAddPage")
	public String showRoleAddPage() {
		return "sys/role/roleAdd";
	}

	/**
	 * 打开设置角色页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("/showSetRoleUserPage")
	public String showRoleEditPage() {
		return "sys/role/setRoleUser";
	}

	/**
	 * 打开设置权限页面<br/>
	 * 
	 * @param roleName
	 * @param roleId
	 * @param roleCode
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showSetAuthPage")
	public String showSetAuthPage(String roleName, String roleId, String roleCode, Model model) {
		if (roleName != null && !roleName.equals("")) {
			model.addAttribute("roleName", roleName);
		}
		if (roleId != null && !roleId.equals("")) {
			model.addAttribute("roleId", roleId);
		}
		if (roleCode != null && !roleCode.equals("")) {
			model.addAttribute("roleCode", roleCode);
		}
		return "sys/role/setAuthorith";
	}

	/**
	 * 打开修改页面<br/>
	 * 
	 * @param roleId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showRoleEditPage")
	public String showRoleEditPage(String roleId, Model model) {

		if (roleId != null && !roleId.equals("")) {
			model.addAttribute("roleId", roleId);
		}
		return "sys/role/roleEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param rowId
	 * @return Dto
	 */
	@RequestMapping("/getRoleById")
	@ResponseBody
	public Dto getRoleById(@RequestParam("roleId") String rowId) {
		Dto param = new BaseDto();
		param.put("id", rowId);
		return roleAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveRoleInfo")
	@ResponseBody
	private Map<String, String> saveRoleInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		Dto param = getParamAsDto(request);
		if (param.get("roleId") == null || "".equals(param.get("roleId"))) {
			try {
				param.put("creator", loginId);
				param.put("createTime", TKDateTimeUtils.getTodayTimeStamp());
				roleAction.insertObject(param);
				map.put(MESSAGE_INFO, "新增成功！");

			} catch (Exception e) {
				map.put(RTN_RESULT, "false");
				map.put(MESSAGE_INFO, e.getMessage());
				return map;
			}
		} else {
			param.put("lastModby", loginId);
			param.put("lastModtime", TKDateTimeUtils.getTodayTimeStamp());
			roleAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param roleId
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteRole")
	@ResponseBody
	public Map<String, String> deleteRole(@RequestParam("roleId") String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Dto param = new BaseDto();
			param.put("roleId", roleId);
			roleAction.deleteObject(param);
			map.put(RTN_RESULT, "true");
			map.put(MESSAGE_INFO, "角色删除成功！");
		} catch (TKBizException e) {
			map.put(RTN_RESULT, "true");
			map.put(MESSAGE_INFO, e.getMessage());
		}
		return map;
	}
}
