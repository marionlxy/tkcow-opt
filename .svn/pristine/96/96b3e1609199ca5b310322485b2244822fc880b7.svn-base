package com.taikang.tkcoww.moduleContent.service.impl;
 
import java.util.List;
import com.taikang.tkcoww.moduleContent.service.IModuleContentService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.tkcoww.moduleContent.model.ModuleContentBO;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ModuleContentServiceImpl
  */
 @Service(IModuleContentService.SERVICE_ID)
 public class ModuleContentServiceImpl extends BaseServiceImpl 
 implements IModuleContentService<ModuleContentBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ModuleContentBO moduleContent) {
		logger.debug("<======ModuleContentServiceImpl--insertModuleContent======>");		
		appDao.insert("ModuleContent.addModuleContent",moduleContent);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ModuleContentBO moduleContent) {
		logger.debug("<======ModuleContentServiceImpl--updateModuleContent======>");
		appDao.update("ModuleContent.updateModuleContent",moduleContent);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ModuleContentBO moduleContent) {
		logger.debug("<======ModuleContentServiceImpl--deleteModuleContent======>");
		appDao.delete("ModuleContent.deleteModuleContent",moduleContent);
	}
	
	/**
      * 查询数据
      */
	public ModuleContentBO findOne(ModuleContentBO moduleContent) {
		logger.debug("<======ModuleContentServiceImpl--findModuleContent======>");
		
		ModuleContentBO moduleContentBackBO=appDao.queryOneObject("ModuleContent.findOneModuleContent", moduleContent);
		return moduleContentBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ModuleContentBO> findAll(ModuleContentBO  moduleContent) {
		logger.debug("<======ModuleContentServiceImpl--findAllModuleContent======>");
		return appDao.queryForEntityList("ModuleContent.findAllModuleContent", moduleContent);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ModuleContentServiceImpl--queryModuleContentForPage======>");
		return appDao.queryForPage("ModuleContent.queryModuleContentForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ModuleContentServiceImpl--findAllMapModuleContent======>");		
		return appDao.queryForMapList("ModuleContent.findAllMapModuleContent", param);
	}
 }
  