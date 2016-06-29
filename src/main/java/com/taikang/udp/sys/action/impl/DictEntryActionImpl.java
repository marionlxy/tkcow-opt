package com.taikang.udp.sys.action.impl;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.tkcoww.moduleImg.model.ModuleImgBO;
import com.taikang.tkcoww.moduleImg.service.IModuleImgService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IDictEntryAction;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.model.UserBO;
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
     * 注入service
     */
    @Resource(name=IModuleImgService.SERVICE_ID)
	private IModuleImgService<ModuleImgBO> moduleImgService;
	
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
     * 修改数据
     */
	public void updateLogoObject(Dto param){
		logger.debug("<======DictEntryAction--updateLogoObject======>");
		
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		dictEntryService.updateObject(dictEntryBO);
		param.put("belongId", param.getAsString("dictId"));
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		UserBO user = SecurityUserHolder.getCurrentUser();
		param.put("modifiedBy", user.getUsername());
		param.put("modifiedTime", time);
		moduleImgService.updateObject(moduleImgBO);
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
     * 查询单条数据
     */
	public Dto findLogoOne(Dto param) {
		logger.debug("<======DictEntryAction--findLogoOne======>");
		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		Dto param2 = new BaseDto();
		param2.put("belongId", param.getAsString("dictId"));
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param2);
		Dto result = dictEntryService.findOne(dictEntryBO).toDto();
		Dto result2 = moduleImgService.findOne(moduleImgBO).toDto();
		
		if (result == null || result.isAllEmpty()) {
			return result2;
		} else {
			if (result2 != null) {
				result.put("modImgName", result2.getAsString("modImgName"));
				result.put("modImgRename", result2.getAsString("modImgRename"));
				result.put("modImgUrl", result2.getAsString("modImgUrl"));
			}
			return result;
		}
		
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
	
	/**
	 * 增加Logo图字典.
	 */
	public void insertLogoDict(Dto param){
		
		Dto param2 = new BaseDto();
		String Id = UUID.randomUUID().toString().replace("-", "");
		param2.put("modImgId", Id);
		param2.put("belongId", param.getAsString("dictId"));
		param2.put("modImgRename", param.getAsString("mod_img_rename"));
		param2.put("modImgName", param.getAsString("mod_img_name"));
		param2.put("modImgUrl", param.getAsString("mod_img_url"));
		UserBO user = SecurityUserHolder.getCurrentUser();
		param2.put("createdBy", user.getUsername());
		Timestamp time = new Timestamp(System.currentTimeMillis());
		param2.put("createTime", time);
		param2.put("delflag", "0");
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param2);
		moduleImgService.insertObject(moduleImgBO);

		DictEntryBO dictEntryBO = BaseDto.toModel(DictEntryBO.class , param);
		dictEntryService.insertObject(dictEntryBO);
	}
}
