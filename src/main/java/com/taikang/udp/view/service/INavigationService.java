package com.taikang.udp.view.service;

import com.taikang.udp.framework.core.service.IBaseService;
import java.util.List;

/**
  * INavigationService
  */
 
public interface INavigationService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "navigationService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> navigationList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> navigationList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> navigationList);	
}
 
 
 