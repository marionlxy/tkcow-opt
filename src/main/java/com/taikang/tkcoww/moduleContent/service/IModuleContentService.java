package com.taikang.tkcoww.moduleContent.service;

import com.taikang.tkcoww.moduleContent.model.ModuleContentBO;
import com.taikang.udp.framework.core.service.IBaseService;

import java.util.Arrays;

/**
  * IModuleContentService
  */
 
 public interface IModuleContentService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "moduleContentService";  	
 	
 	public ModuleContentBO findNewOne(ModuleContentBO moduleContent) ;
 }
 
 
 