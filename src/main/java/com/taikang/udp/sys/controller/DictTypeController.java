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
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.IDictTypeAction;
import com.taikang.udp.sys.util.vo.Tree;

/**
 * 
 * 数据字典类型维护<br/>
 * 
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年1月21日 下午3:33:23
 */
@Controller("dictTypeController")
@RequestMapping(value = "/sys/dictType")
public class DictTypeController extends BaseController {

	@Resource(name = IDictTypeAction.ACTION_ID)
	private IDictTypeAction dictTypeAction;

	/**
	 * 打开主查询页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("/showDictTypeIndexPage")
	public String showDictTypeIndexPage() {
		return "sys/dict/dictTypeIndex";
	}

	/**
	 * 查询字典类别树<br/>
	 * 
	 * @return List<Tree>
	 */
	@RequestMapping("/getDictTypeTree")
	@ResponseBody
	public List<Tree> getDictTypeTree() {
		List<Tree> backList = new ArrayList<Tree>();
		Tree tree = new Tree();
		tree.setChildren(dictTypeAction.findAllDictType());
		tree.setText("所有类别");
		backList.add(tree);

		return backList;
	}

	/**
	 * 查询字典类别树形菜单<br/>
	 * 
	 * @return List<Tree>
	 */
	@RequestMapping("/getAllDictTypeTree")
	@ResponseBody
	public List<Tree> getAllDictTypeTree() {
		List<Tree> backList = new ArrayList<Tree>();
		Tree tree = new Tree();
		tree.setChildren(dictTypeAction.findAllDictTypeTree());
		tree.setText("所有类别");
		backList.add(tree);

		return backList;
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getDictTypeList")
	@ResponseBody
	public Map<String, Object> getDictTypeList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = dictTypeAction.queryForPage(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面<br/>
	 * 
	 * @param dictTypeId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showDictTypeEditPage")
	public String showDictTypeEditPage(String dictTypeId, Model model) {
		if (dictTypeId != null && !dictTypeId.equals("")) {
			model.addAttribute("dictTypeId", dictTypeId);
		}
		return "sys/dict/dictTypeEdit";
	}

	/**
	 * 打开新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/showAddTypeEditPage")
	public String toAddTypeEditPage() {
		return "sys/dict/dictTypeAdd";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param dictTypeId
	 * @return Dto
	 */
	@RequestMapping("/getDictTypeById")
	@ResponseBody
	public Dto getDictTypeById(@RequestParam("dictTypeId") String dictTypeId) {
		Dto param = new BaseDto();
		param.put("dictTypeId", dictTypeId);
		return dictTypeAction.findOne(param);
	}

	/**
	 * 保存新增的记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveDictTypeInfo")
	@ResponseBody
	private Map<String, String> saveDictTypeInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		dictTypeAction.insertObject(param);
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 保存修改的记录，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveEditDictTypeInfo")
	@ResponseBody
	private Map<String, String> saveEditDictTypeInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = getParamAsDto(request);
		dictTypeAction.updateObject(param);
		map.put(MESSAGE_INFO, "修改成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param dictType
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteDictType")
	@ResponseBody
	public Map<String, String> deleteDictType(@RequestParam("dictType") String dictType) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Dto param = new BaseDto();
			param.put("dictType", dictType);
			dictTypeAction.deleteObject(param);

			map.put(RTN_RESULT, "true");
			map.put(MESSAGE_INFO, "字典类别删除成功！");
		} catch (TKBizException e) {
			map.put(RTN_RESULT, "true");
			map.put(MESSAGE_INFO, e.getMessage());
		}
		return map;
	}
}
