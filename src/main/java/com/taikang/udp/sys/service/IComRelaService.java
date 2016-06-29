package com.taikang.udp.sys.service;

import com.taikang.udp.framework.core.service.IBaseService;
import com.taikang.udp.sys.model.ComRelaBO;

/**
  * IComRelaService
  */
 
 public interface IComRelaService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "comRelaService";  	
 	
	 /**
	  * 删除数据ByParentId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByParentId(ComRelaBO comRela);
	
	 /**
	  * 删除数据 ByChildId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByChildId(ComRelaBO comRela);
	
	/**
	  * 删除数据 ByChildId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByParentIdAndChild(ComRelaBO comRela);
 }
 
 
 