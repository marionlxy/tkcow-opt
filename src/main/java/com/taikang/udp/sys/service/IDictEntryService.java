package com.taikang.udp.sys.service;

import java.util.List;

import com.taikang.udp.framework.common.cache.ICacheService;
import com.taikang.udp.framework.core.service.IBaseService;

/**
  * IDictEntryService
  */
 
 public interface IDictEntryService<T> extends IBaseService<T>,ICacheService<String, List<T>>{
 
 	static final String SERVICE_ID = "dictEntryService";
 	
 	public static final String DICT_ENTRY_KEY= "dictEntryKey";
 }
 
 
 