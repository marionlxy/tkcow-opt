package com.taikang.tkcoww.moduleImg.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.tkcoww.moduleImg.action.IModuleImgAction;
import com.taikang.tkcoww.moduleImg.model.ModuleImgBO;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.moduleImg.service.IModuleImgService;

/**
  * ModuleImgAction
  */
  @Service(IModuleImgAction.ACTION_ID)
public class ModuleImgActionImpl extends BaseActionImpl 
  implements IModuleImgAction {

    /**
      * 注入service
      */
    @Resource(name=IModuleImgService.SERVICE_ID)
	private IModuleImgService<ModuleImgBO> moduleImgService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ModuleImgAction--addModuleImg======>");
		
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param);
		moduleImgService.insertObject(moduleImgBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ModuleImgAction--updateModuleImg======>");
		
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param);
		moduleImgService.updateObject(moduleImgBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ModuleImgAction--deleteModuleImg======>");
		
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param);
		moduleImgService.deleteObject(moduleImgBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ModuleImgAction--findOneModuleImg======>");
		
		ModuleImgBO moduleImgBO = BaseDto.toModel(ModuleImgBO.class , param);
		return moduleImgService.findOne(moduleImgBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ModuleImgAction--findAllModuleImg======>");
				
		return moduleImgService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ModuleImgAction--queryModuleImgForPage======>");
		
		return moduleImgService.queryForPage(currentPage);
	}	
}
