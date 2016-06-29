package com.taikang.udp.sys.service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.service.IBaseService;
import java.util.List;


/**
  * IRoleService
  */
 
public interface IRoleService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "roleService";  
 	
	
	List<T> listByResId(T t);
	
	List<T> listByUserId(T t);
	public List<Dto> getUsersRoleList(Dto param);
}
 
 
 