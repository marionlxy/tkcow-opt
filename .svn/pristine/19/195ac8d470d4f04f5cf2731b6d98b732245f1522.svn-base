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
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IRoleMenuAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;

/**
 * RoleMenuController
 */
@Controller("roleMenuController")
@RequestMapping(value = "/roleMenu")
public class RoleMenuController extends BaseController {

	@Resource(name = IRoleMenuAction.ACTION_ID)
	private IRoleMenuAction roleMenuAction;

	/**
	 * 打开主查询页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showRoleMenuIndexPage() {
		return "roleMenu/roleMenuIndex";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getRoleMenuList")
	@ResponseBody
	public Map<String, Object> getRoleMenuList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = roleMenuAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/showRoleMenuEditPage")
	public String showRoleMenuEditPage(String rowId, Model model) {

		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}
		return "roleMenu/roleMenuEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/getRoleMenuById")
	@ResponseBody
	public Dto getRoleMenuById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		return roleMenuAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/saveRoleMenuInfo")
	@ResponseBody
	private Map<String, String> saveRoleMenuInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			roleMenuAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			roleMenuAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 保存新增记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/saveRoleMenu")
	@ResponseBody
	private Map<String, String> saveRoleMenu(HttpServletRequest request, String roleId, String menuIds) {
		Map<String, String> map = new HashMap<String, String>();
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		roleMenuAction.batchSaveRoleMenu(roleId, loginId, menuIds);
		map.put(MESSAGE_INFO, "授权成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录
	 */
	@RequestMapping(value = "/deleteRoleMenu")
	@ResponseBody
	public Map<String, String> deleteRoleMenu(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("rowId", rowId);
		roleMenuAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
}
