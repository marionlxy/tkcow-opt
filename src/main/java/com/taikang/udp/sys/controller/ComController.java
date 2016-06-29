package com.taikang.udp.sys.controller;

import java.util.ArrayList;
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
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.framework.core.web.log.Log;
import com.taikang.udp.framework.core.web.log.LogLevel;
import com.taikang.udp.framework.core.web.log.OperType;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IComAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;
import com.taikang.udp.sys.util.vo.Tree;

/**
 * 
 * 机构管理处理<br/>
 * 
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年1月21日 下午3:07:06
 */
@Controller("comController")
@RequestMapping(value = "/com")
public class ComController extends BaseController {

	@Resource(name = IComAction.ACTION_ID)
	private IComAction comAction;

	/**
	 * 打开主查询页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("")
	public String showComIndexPage() {
		return "sys/com/comIndex";
	}

	/**
	 * 查询机构树信息<br/>
	 * 
	 * @param id
	 * @return List<Tree>
	 */
	@RequestMapping("/getComTree")
	@ResponseBody
	public List<Tree> getComTree(@RequestParam("id") String id) {
		List<Tree> rtnLst = null;
		String array[] = null;
		if (id != null) {
			array = id.split(",");
			if (array != null && (array.length == 1)) {
				// 获取机构树信息
				rtnLst = comAction.getComInfo();
			} else if (array != null && (array.length == 2)) {
				// 获取机构树信息
				rtnLst = comAction.getChildrenTreeLst(Integer.valueOf(array[1]), "open");
			}
		} else {
			rtnLst = new ArrayList<Tree>();
		}
		return rtnLst;
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getComList")
	@ResponseBody
	public Map<String, Object> getComList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = comAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 按条件查询机构信息<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getByconlist")
	@ResponseBody
	public Map<String, Object> getByconlist(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = comAction.getByconlist(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 查询指定机构的子机构信息<br/>
	 * 
	 * @param request
	 * @param page
	 * @param comId
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getChildList")
	@ResponseBody
	public Map<String, Object> getChildList(HttpServletRequest request, CurrentPage page,
			@RequestParam("Com_Id") String comId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// request.setAttribute("Com_Id", comId);
		Dto paramDto = getParamAsDto(request);
		paramDto.put("com_id", comId);
		page.setParamObject(paramDto);
		CurrentPage currentPage = comAction.getChildList(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}

	/**
	 * 打开新增页面<br/>
	 * 
	 * @param rowId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showComAddPage")
	public String showComAddPage(String Parent_Id, Model model) {
		if (Parent_Id != null && !Parent_Id.equals("")) {
			model.addAttribute("Parent_Id", Parent_Id);
		}
		return "sys/com/comAdd";
	}

	/**
	 * 打开修改页面<br/>
	 * 
	 * @param rowId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showComEditPage")
	public String showComEditPage(String Com_Id, String Parent_Id, Model model) {

		if (Parent_Id != null && !Parent_Id.equals("")) {
			model.addAttribute("Parent_Id", Parent_Id);
		}
		if (Com_Id != null && !Com_Id.equals("")) {
			model.addAttribute("Com_Id", Com_Id);
		}
		return "sys/com/comEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param rowId
	 * @return Dto
	 */
	@RequestMapping("/getComById")
	@ResponseBody
	public Dto getComById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("comId", rowId);
		return comAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中<br>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveComInfo")
	@ResponseBody
	@Log(message = "修改机构信息", operType = OperType.READ, operName = "保存机构信息", level = LogLevel.TRACE)
	private Map<String, String> saveComInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Dto param = getParamAsDto(request);
			//LoginUser user = UserUtils.getUser();
			UserBO user = SecurityUserHolder.getCurrentUser();
			String loginId = String.valueOf(user.getId());
			if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
				param.put("createUser", loginId);
				param.put("createTime", TKDateTimeUtils.getTodayTimeStamp());
				param.put("lastModifyUser", loginId);
				param.put("lastModifyTime", TKDateTimeUtils.getTodayTimeStamp());
				comAction.savwObject(param);
				map.put(MESSAGE_INFO, "机构编辑成功！");
			} else {
				param.put("comId", param.get("rowId"));
				param.put("lastModifyUser", loginId);
				param.put("lastModifyTime", TKDateTimeUtils.getTodayTimeStamp());
				comAction.updateObject(param);
				map.put(MESSAGE_INFO, "机构更新成功！");
			}
			map.put(RTN_RESULT, "true");
		} catch (Exception e) {
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, e.getMessage());
			return map;
		}
		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param rowId
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteCom")
	@ResponseBody
	@Log(message = "删除指定机构", operType = OperType.READ, operName = "删除机构", level = LogLevel.TRACE)
	public Map<String, String> deleteCom(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Dto param = new BaseDto();
			param.put("comId", rowId);
			comAction.deleteObject(param);

			map.put(RTN_RESULT, "true");
			map.put(MESSAGE_INFO, "操作成功！");
		} catch (Exception e) {
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, e.getMessage());
			return map;
		}
		return map;
	}
}
