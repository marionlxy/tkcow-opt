package com.taikang.udp.sys.service;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.service.IBaseService;
import com.taikang.udp.sys.util.vo.MenuTree;

/**
  * IMenuService
  */
 
 public interface IMenuService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "menuService";  	
 	
 	public List<Dto> findAllMenuTreeMap(Dto param);
 	
 	public List<Dto> findAllMenuTreeLeafMap(Dto param);
 	
 	public void bachDealButtons(Dto param);
 	
	/**
	 * 获取指定用户页面按钮信息<br/>
	 * @param param
	 * @return
	 */
    public List<Dto> getbtnAccess(Dto param);
    
	/**
	 * 获取指定菜单的树机构信息<br/>
	 * @param menuId
	 * @param state
	 * @param currId
	 * @param parentIcon
	 * @param childIcon
	 * @return
	 */
	public List<MenuTree> getMenuTree(String menuId, String state,
	        String currId,String parentIcon, String childIcon);
 	
 } 