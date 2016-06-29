package com.taikang.udp.manage.service;

import com.taikang.udp.framework.core.service.IBaseService;
import java.util.List;
import java.util.Arrays;

/**
  * IAdvertService
  */
 
public interface IAdvertService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "advertService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> advertList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> advertList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> advertList);	
}
 
 
 