package com.taikang.tkcoww.moduleDes.service;

import com.taikang.tkcoww.moduleDes.model.ModuleDesBO;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.IBaseService;

import java.util.Arrays;

/**
  * IModuleDesService
  */
 
 public interface IModuleDesService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "moduleDesService";

 	//不带图内容模板
	public CurrentPage getWithoutImgListModuleList(CurrentPage currentPage);
	//发展历程模板
	public CurrentPage getDevelopCourseModuleList(CurrentPage currentPage);
	// 带图内容模板
	public CurrentPage getImgListModuleList(CurrentPage currentPage);
	//查询单个modDes
	public ModuleDesBO findOneModDes(ModuleDesBO moduleDes);
	public BaseBO findNewOne(ModuleDesBO moduleDesBO);
	
 }
 
 
 