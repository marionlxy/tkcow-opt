package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IDictEntryAction;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.service.IDictEntryService;
import com.taikang.udp.sys.util.enums.Status;

/**
  * DictEntryAction
  */
  @Service(IDictEntryAction.ACTION_ID)
public class DictEntryActionImpl extends BaseActionImpl 
  implements IDictEntryAction {

    /**
      * 注入service
      */
    @Resource(name=IDictEntryService.SERVICE_ID)
	private IDictEntryService<DictEntryBO> dictEntryService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======DictEntryAction--addDictEntry======>");
		
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		dictEntryService.insertObject(dictEntryBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======DictEntryAction--updateDictEntry======>");
		
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		dictEntryService.updateObject(dictEntryBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======DictEntryAction--deleteDictEntry======>");
		
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		dictEntryService.deleteObject(dictEntryBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======DictEntryAction--findOneDictEntry======>");
		
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		return dictEntryService.findOne(dictEntryBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======DictEntryAction--findAllDictEntry======>");
				
		return dictEntryService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======DictEntryAction--queryDictEntryForPage======>");
		currentPage.getParamObject().put("status", Status.Normal.getCode());//只查询有效状态的数据
		return dictEntryService.queryForPage(currentPage);
	}	
}
