package com.taikang.tkcoww.moduleContent.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.tkcoww.moduleContent.action.IModuleContentAction;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.tkcoww.moduleContent.service.IModuleContentService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.tkcoww.moduleContent.model.ModuleContentBO;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;

/**
  * ModuleContentAction
  */
  @Service(IModuleContentAction.ACTION_ID)
public class ModuleContentActionImpl extends BaseActionImpl 
  implements IModuleContentAction {

    /**
      * 注入service
      */
    @Resource(name=IModuleContentService.SERVICE_ID)
	private IModuleContentService<ModuleContentBO> moduleContentService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ModuleContentAction--addModuleContent======>");
		
		ModuleContentBO moduleContentBO = BaseDto.toModel(ModuleContentBO.class , param);
		moduleContentService.insertObject(moduleContentBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ModuleContentAction--updateModuleContent======>");
		
		ModuleContentBO moduleContentBO = BaseDto.toModel(ModuleContentBO.class , param);
		moduleContentService.updateObject(moduleContentBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ModuleContentAction--deleteModuleContent======>");
		
		ModuleContentBO moduleContentBO = BaseDto.toModel(ModuleContentBO.class , param);
		moduleContentService.deleteObject(moduleContentBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ModuleContentAction--findOneModuleContent======>");
		
		ModuleContentBO moduleContentBO = BaseDto.toModel(ModuleContentBO.class , param);
		return moduleContentService.findOne(moduleContentBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
     * 查询单条数据(新)
     */
	public Dto findNewOne(Dto param) {
		logger.debug("<======ModuleContentAction--findOneModuleContent======>");
		
		ModuleContentBO moduleContentBO = BaseDto.toModel(ModuleContentBO.class , param);
		return moduleContentService.findNewOne(moduleContentBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ModuleContentAction--findAllModuleContent======>");
				
		return moduleContentService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ModuleContentAction--queryModuleContentForPage======>");
		
		return moduleContentService.queryForPage(currentPage);
	}	
}
