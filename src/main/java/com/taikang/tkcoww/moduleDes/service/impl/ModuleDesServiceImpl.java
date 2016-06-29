package com.taikang.tkcoww.moduleDes.service.impl;
 
import java.util.List;

import com.taikang.tkcoww.moduleDes.model.ModuleDesBO;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;

import java.util.Arrays;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;

import org.springframework.stereotype.Service;

import com.taikang.tkcoww.moduleDes.service.IModuleDesService;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ModuleDesServiceImpl
  */
 @Service(IModuleDesService.SERVICE_ID)
 public class ModuleDesServiceImpl extends BaseServiceImpl 
 implements IModuleDesService<ModuleDesBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ModuleDesBO moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--insertModuleDes======>");		
		appDao.insert("ModuleDes.addModuleDes",moduleDes);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ModuleDesBO moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--updateModuleDes======>");
		appDao.update("ModuleDes.updateModuleDes",moduleDes);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ModuleDesBO moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--deleteModuleDes======>");
		appDao.delete("ModuleDes.deleteModuleDes",moduleDes);
	}
	
	/**
      * 查询数据
      */
	public ModuleDesBO findOne(ModuleDesBO moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--findModuleDes======>");
		
		ModuleDesBO moduleDesBackBO=appDao.queryOneObject("ModuleDes.findOneModuleDes", moduleDes);
		return moduleDesBackBO;		
	}
	@Override
	public BaseBO findNewOne(ModuleDesBO moduleDesBO) {
		logger.debug("<======ModuleDesServiceImpl--findNewOne======>");
		
		ModuleDesBO moduleDesBackBO=appDao.queryOneObject("ModuleDes.findNewOne", moduleDesBO);
		return moduleDesBackBO;		
	}
	/**
     * 查询数据
     */
	public ModuleDesBO findOneModDes(ModuleDesBO moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--findModuleDes======>");
		
		ModuleDesBO moduleDesBackBO=appDao.queryOneObject("ModuleDes.findOneModDes", moduleDes);
		return moduleDesBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ModuleDesBO> findAll(ModuleDesBO  moduleDes) {
		logger.debug("<======ModuleDesServiceImpl--findAllModuleDes======>");
		return appDao.queryForEntityList("ModuleDes.findAllModuleDes", moduleDes);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ModuleDesServiceImpl--queryModuleDesForPage======>");
		return appDao.queryForPage("ModuleDes.queryModuleDesForPage", currentPage);
	}
	/**
	 * 分页查询 不带图内容模板
	 */
	public CurrentPage getWithoutImgListModuleList(CurrentPage currentPage) {
		logger.debug("<======ModuleDesServiceImpl--queryModuleDesForPage======>");
		return appDao.queryForPage("ModuleDes.getWithoutImgListModuleList", currentPage);
	}
	
	/**
	 * 分页查询 发展历程内容模板
	 */
	public CurrentPage getDevelopCourseModuleList(CurrentPage currentPage) {
		logger.debug("<======ModuleDesServiceImpl--queryModuleDesForPage======>");
		return appDao.queryForPage("ModuleDes.getDevelopCourseModuleList", currentPage);
	}
	
	/**
	 * 分页查询 带图内容模板
	 */
	public CurrentPage getImgListModuleList(CurrentPage currentPage) {
		logger.debug("<======ModuleDesServiceImpl--queryModuleDesForPage======>");
		return appDao.queryForPage("ModuleDes.getImgListModuleList", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ModuleDesServiceImpl--findAllMapModuleDes======>");		
		return appDao.queryForMapList("ModuleDes.findAllMapModuleDes", param);
	}
 }
  