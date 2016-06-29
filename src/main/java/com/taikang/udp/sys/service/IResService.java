package com.taikang.udp.sys.service;

import com.taikang.udp.framework.core.service.IBaseService;
import java.util.List;
import java.util.Arrays;

/**
  * IResService
  */
 
public interface IResService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "resService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> resList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> resList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> resList);	
}
 
 
 