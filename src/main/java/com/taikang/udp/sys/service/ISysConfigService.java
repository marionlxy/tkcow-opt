package com.taikang.udp.sys.service;

import com.taikang.udp.framework.core.service.IBaseService;
import java.util.List;
/**
  * ISysConfigService
  */
 
public interface ISysConfigService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "sysConfigService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> sysConfigList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> sysConfigList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> sysConfigList);	
}
 
 
 