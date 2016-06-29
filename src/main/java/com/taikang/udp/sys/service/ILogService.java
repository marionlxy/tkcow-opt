package com.taikang.udp.sys.service;

import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.IBaseService;

/**
  * ILogService
  */
 
 public interface ILogService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "logService";  
 	
	/**
	 * 分页查询数据
	 * @param currentPage
	 * @return CurrentPage
	 */
	public CurrentPage queryByconForPage(CurrentPage currentPage);
 }
 
 
 