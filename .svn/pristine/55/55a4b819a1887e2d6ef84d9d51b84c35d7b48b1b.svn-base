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
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.IDictEntryAction;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.util.DictUtils;

/**
 * 
 * 数据字典管理<br/>
 * 
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年1月21日 下午3:11:59
 */
@Controller("dictEntryController")
@RequestMapping(value = "/sys/dictEntry")
public class DictEntryController extends BaseController {

	@Resource(name = IDictEntryAction.ACTION_ID)
	private IDictEntryAction dictEntryAction;

	/**
	 * 打开主查询页面 <br/>
	 * 
	 * @return String
	 */
	@RequestMapping("")
	public String showDictEntryIndexPage() {
		return "sys/dict/dictEntryIndex";
	}

	/**
	 * 查询信息列表<br/>
	 * 
	 * @param request
	 * @param page
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getDictEntryList")
	@ResponseBody
	public Map<String, Object> getDictEntryList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = dictEntryAction.queryForPage(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面<br/>
	 * 
	 * @param dictTypeId
	 * @param dictId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showDictEntryEditPage")
	public String showDictEntryEditPage(String dictTypeId, String dictId, Model model) {
		if (!TKStringUtils.isNullOrEmpty(dictTypeId)) {
			model.addAttribute("dictTypeId", dictTypeId);
		}
		if (!TKStringUtils.isNullOrEmpty(dictId)) {
			model.addAttribute("dictId", dictId);
		}
		return "sys/dict/dictEntryEdit";
	}

	/**
	 * 打开新增或修改页面<br/>
	 * 
	 * @param dictTypeId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showDictEntryAddPage")
	public String showDictEntryAddPage(String dictTypeId, Model model) {
		if (!TKStringUtils.isNullOrEmpty(dictTypeId)) {
			model.addAttribute("dictTypeId", dictTypeId);
		}
		if (dictTypeId != null && "owlogo".equals(dictTypeId)) {
			return "sys/dict/dictEntryAddUpLoad";
		} else {
			return "sys/dict/dictEntryAdd";
		}
	}

	/**
	 * 打开用户管理类别页面<br/>
	 * 
	 * @return String
	 */
	@RequestMapping("/toDictEntry")
	public String toDictEntry() {
		return "sys/dict/dictTypeIndex";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面<br/>
	 * 
	 * @param dictTypeId
	 * @param dictId
	 * @return Dto
	 */
	@RequestMapping("/getDictEntryById")
	@ResponseBody
	public Dto getDictEntryById(@RequestParam("dictTypeId") String dictTypeId, @RequestParam("dictId") String dictId) {
		Dto param = new BaseDto();
		param.put("dictTypeId", dictTypeId);
		param.put("dictId", dictId);
		return dictEntryAction.findOne(param);
	}

	/**
	 * 保存新增，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveDictEntryAdd")
	@ResponseBody
	private Map<String, String> saveDictEntryAdd(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		dictEntryAction.insertObject(param);
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}
	
	/**
	 * 保存新增logo，将其持久化到数据库中<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/saveLogoEntry")
	@ResponseBody
	private Map<String, String> saveLogoAdd(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		dictEntryAction.insertObject(param);
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 保存修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/saveDictEntryEdit")
	@ResponseBody
	private Map<String, String> saveDictEntryEdit(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		dictEntryAction.updateObject(param);
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "更新成功！");

		return map;
	}

	/**
	 * 删除一条或多条记录<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteDictEntry")
	@ResponseBody
	public Map<String, String> deleteDictEntry(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		dictEntryAction.deleteObject(param);
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 获取指定字典类型的字典数据<br/>
	 * 
	 * @param dictTypeId
	 * @param dictId
	 * @return Dto
	 */
	@RequestMapping("/getDictEntryByDictType")
	@ResponseBody
	public List getDictEntryByDictType(@RequestParam("dictTypeId") String dictType) {
		List<DictEntryBO> list = DictUtils.getDictEntryList(dictType);
		List<Dto> listDto = new ArrayList<Dto>();
		Dto tmpDto = new BaseDto();
		for (DictEntryBO bo : list) {
			tmpDto = new BaseDto();
			tmpDto.put("id", bo.getDictId());
			tmpDto.put("text", bo.getDictName());
			listDto.add(tmpDto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listDto);
		return listDto;

	}
}
