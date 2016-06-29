package com.taikang.tkcoww.moduleImg.service.impl;
 
import com.taikang.tkcoww.moduleImg.model.ModuleImgBO;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.moduleImg.service.IModuleImgService;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ModuleImgServiceImpl
  */
 @Service(IModuleImgService.SERVICE_ID)
 public class ModuleImgServiceImpl extends BaseServiceImpl 
 implements IModuleImgService<ModuleImgBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ModuleImgBO moduleImg) {
		logger.debug("<======ModuleImgServiceImpl--insertModuleImg======>");		
		appDao.insert("ModuleImg.addModuleImg",moduleImg);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ModuleImgBO moduleImg) {
		logger.debug("<======ModuleImgServiceImpl--updateModuleImg======>");
		appDao.update("ModuleImg.updateModuleImg",moduleImg);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ModuleImgBO moduleImg) {
		logger.debug("<======ModuleImgServiceImpl--deleteModuleImg======>");
		appDao.delete("ModuleImg.deleteModuleImg",moduleImg);
	}
	
	/**
      * 查询数据
      */
	public ModuleImgBO findOne(ModuleImgBO moduleImg) {
		logger.debug("<======ModuleImgServiceImpl--findModuleImg======>");
		
		ModuleImgBO moduleImgBackBO=appDao.queryOneObject("ModuleImg.findOneModuleImg", moduleImg);
		return moduleImgBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ModuleImgBO> findAll(ModuleImgBO  moduleImg) {
		logger.debug("<======ModuleImgServiceImpl--findAllModuleImg======>");
		return appDao.queryForEntityList("ModuleImg.findAllModuleImg", moduleImg);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ModuleImgServiceImpl--queryModuleImgForPage======>");
		return appDao.queryForPage("ModuleImg.queryModuleImgForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ModuleImgServiceImpl--findAllMapModuleImg======>");		
		return appDao.queryForMapList("ModuleImg.findAllMapModuleImg", param);
	}
 }
  