package com.taikang.tkcoww.com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.util.DictUtils;

@Controller("iuCommonController")
@RequestMapping(value="")
public class IUCommonController extends BaseController {
	
	/**
	 * 获取【下拉框】指定字典类型的字典数据<br/>
	 * 
	 * @param dictType
	 * @param dictId
	 * @return list
	 * @author t-wuke
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getDictCombox")
	@ResponseBody
	public List getDictCombox(@RequestParam("dictType") String dictType) {
		List<DictEntryBO> list = DictUtils.getDictEntryList(dictType);
		List<Dto> listDto = new ArrayList<Dto>();
		Dto tmpDto = new BaseDto();
		for (DictEntryBO bo : list) {
			tmpDto = new BaseDto();
			tmpDto.put("dictId", bo.getDictId());
			tmpDto.put("dictName", bo.getDictName());
			listDto.add(tmpDto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listDto);
		return listDto;

	}
	
	/**
	 * 获取【列表】全部字典数据
	 * 
	 * @param dictTypes
	 * @return totalList
	 * @author t-wuke
	 */
	@RequestMapping(value = "/getDictList")
	@ResponseBody
	public List<DictEntryBO> getDictList(@RequestParam(value = "dictTypes") String[] dictTypes){
		List<DictEntryBO> totalList = new ArrayList<DictEntryBO>();
		for (String dictType : dictTypes) {
			List<DictEntryBO> entryList = DictUtils.getDictEntryList(dictType);
			totalList.addAll(entryList);
		}

		return totalList;
	}
}
