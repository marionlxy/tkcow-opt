package com.taikang.udp.sys.controller;

import java.util.ArrayList;
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
import com.taikang.udp.sys.action.IMenuAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;
import com.taikang.udp.sys.util.vo.MenuTree;

/**
 * 
 * MenuController菜单按钮控制Controller<br/>
 * 
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2014年12月24日 上午10:04:26
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("menuController")
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

	@Resource(name = IMenuAction.ACTION_ID)
	private IMenuAction menuAction;

	/**
	 * 打开按钮主查询页面<br/>
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String showMenuIndexPage() {
		return "sys/menu/menuIndex";
	}

	/**
	 * 打开设置按钮页面<br/>
	 * 
	 * @param menuId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showEditButtonPage")
	public String showEditButtonPage(@RequestParam("menuId") String menuId, Model model) {
		if (menuId != null && !menuId.equals("")) {
			model.addAttribute("menuId", menuId);
		}
		return "sys/menu/buttonIndex";
	}

	/**
	 * 查询所有菜单及按钮信息列表<br/>
	 * 
	 * @param request
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/getMenuTreeList")
	@ResponseBody
	public Map<String, Object> getMenuTreeList(HttpServletRequest request, @RequestParam("roleId") String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = menuAction.findAllMenuTreeMap(getParamAsDto(request));
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = (Map<String, Object>) list.get(0);
		String _havedMenuId = (String) map2.get("_havedMenuId");
		map.put("_havedMenuId", _havedMenuId);
		map.put("rows", list);
		map.put("total", list.size());

		return map;
	}

	/**
	 * 获取指定菜单的子菜单树信息<br/>
	 * 
	 * @param menuId
	 * @return List<Tree>
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public List<MenuTree> getMenuTree(@RequestParam("id") String menuId, HttpServletRequest request) {

		List<MenuTree> rtnLst = null;
		if (menuId != null) {
			rtnLst = menuAction.getMenuTree(menuId, "open", menuId, "icon-text_list_numbers", "icon-page_forward");
		} else {
			rtnLst = new ArrayList<MenuTree>();
		}
		return rtnLst;
	}

	/**
	 * 查询所有菜单节点信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getMenuList4Tree")
	@ResponseBody
	public Map<String, Object> getMenuList4Tree(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		List list = menuAction.findAllMenuTreeLeafMap(getParamAsDto(request));
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = (Map<String, Object>) list.get(0);
		map.put("rows", list);
		map.put("total", list.size());

		return map;
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getMenuList")
	@ResponseBody
	public Map<String, Object> getMenuList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = menuAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 查询按钮信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return 指定菜单的分页列表数据
	 */
	@RequestMapping("/getBtnList")
	@ResponseBody
	public Map<String, Object> getBtnList(@RequestParam("menuId") String menuId, HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Dto reqDto = getParamAsDto(request);
		reqDto.put("parent_id", menuId);
		reqDto.put("menu_status", "1");
		reqDto.put("is_show", "0");
		page.setParamObject(reqDto);
		CurrentPage currentPage = menuAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 查询父菜单信息列表<br/>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getParentMenu")
	@ResponseBody
	public List<Dto> getParentMenuList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		Dto reqDto = getParamAsDto(request);
		// reqDto.put("parent_id", 0);
		reqDto.put("menu_status", "1");
		reqDto.put("is_show", "1");
		reqDto.put("is_leaf", "0");

		List<Dto> list = menuAction.findAll(reqDto);
		return list;
	}

	/**
	 * 打开菜单新增页面<br/>
	 * 
	 * @param isLeaf
	 * @param model
	 * @return
	 */
	@RequestMapping("/showMenuAddPage")
	public String showMenuAddPage(@RequestParam("isLeaf") String isLeaf, @RequestParam("parent") String parent,
			Model model) {
		model.addAttribute("isLeaf", isLeaf);
		model.addAttribute("parentId", parent);
		return "sys/menu/menuAdd";
	}

	/**
	 * 打开菜单修改页面<br/>
	 * 
	 * @param menuId
	 * @param isLeaf
	 * @param model
	 * @return
	 */
	@RequestMapping("/showMenuEditPage")
	public String showMenuEditPage(@RequestParam("menuId") String menuId, @RequestParam("isLeaf") String isLeaf,
			Model model) {

		if (menuId != null && !menuId.equals("")) {
			model.addAttribute("menuId", menuId);
		}
		if (isLeaf != null && !isLeaf.equals("")) {
			model.addAttribute("isLeaf", isLeaf);
		}

		return "sys/menu/menuEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/getMenuById")
	@ResponseBody
	public Dto getMenuById(@RequestParam("menuId") String menuId) {
		Dto param = new BaseDto();
		param.put("menuId", menuId);
		return menuAction.findOne(param);
	}

	/**
	 * 保存新增菜单记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveMenuInfo")
	@ResponseBody
	private Map<String, String> saveMenuInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		menuAction.insertObject(param);
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 保存修改的菜单记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editMenuInfo")
	@ResponseBody
	private Map<String, String> editMenuInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		if (param.get("menuId") == null || "".equals(param.get("menuId"))) {
			menuAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			menuAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param rowId
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	public Map<String, String> deleteMenu(@RequestParam("menuId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("menuId", rowId);
		menuAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 批量保存按钮信息，将其持久化到数据库中<br/>
	 * 
	 * @param buttons
	 * @return
	 */
	@RequestMapping("/saveButtons")
	@ResponseBody
	private Map<String, String> saveButtons(@RequestBody Map buttonMaps, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());

		Dto param = getParamAsDto(request);
		param.put("loginId", loginId);
		param.put("buttonMaps", buttonMaps);
		String parentId = param.getAsString("parentId");
		menuAction.bachDealButtons(param);
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 取得页面按钮的权限信息<br/>
	 * 
	 * @param reqMenuId
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/getbtnAccess")
	@ResponseBody
	public Map<String, Object> getbtnAccess(@RequestParam("reqMenuId") String reqMenuId, HttpServletRequest request) {
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		Dto param = getParamAsDto(request);
		param.put("reqMenuId", reqMenuId);
		param.put("loginId", loginId);

		// 获取页面按钮信息
		List<Dto> buttonLst = menuAction.getbtnAccess(param);
		if (buttonLst != null && buttonLst.size() > 0) {
			String[] nohavedBtn = new String[buttonLst.size()];
			for (int i = 0; i < buttonLst.size(); i++) {
				// 组织需要置灰的按钮信息
				if (buttonLst.get(i).getAsString("ishaveBtn") == null
						|| "".equals(buttonLst.get(i).getAsString("ishaveBtn"))) {
					nohavedBtn[i] = buttonLst.get(i).getAsString("menu_name");
				}
			}
			map.put("nohavedBtn", nohavedBtn);
		}
		map.put(RTN_RESULT, true);
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
}
