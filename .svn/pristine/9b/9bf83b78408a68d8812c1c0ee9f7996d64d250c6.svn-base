package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IMenuAction;
import com.taikang.udp.sys.model.MenuBO;
import com.taikang.udp.sys.model.RoleMenuBO;
import com.taikang.udp.sys.service.IMenuService;
import com.taikang.udp.sys.service.IRoleMenuService;
import com.taikang.udp.sys.util.vo.MenuTree;

/**
  * MenuAction
  */
  @Service(IMenuAction.ACTION_ID)
public class MenuActionImpl extends BaseActionImpl 
  implements IMenuAction {

    /**
      * 注入service
      */
    @Resource(name=IMenuService.SERVICE_ID)
	private IMenuService<MenuBO> menuService;
    
    @Resource(name=IRoleMenuService.SERVICE_ID)
	private IRoleMenuService<RoleMenuBO> roleMenuService;
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======MenuAction--addMenu======>");
		
		MenuBO menuBO = BaseDto.toModel(MenuBO.class , param);
		menuService.insertObject(menuBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======MenuAction--updateMenu======>");
		
		MenuBO menuBO = BaseDto.toModel(MenuBO.class , param);
		menuService.updateObject(menuBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======MenuAction--deleteMenu======>");
		MenuBO menuBO = BaseDto.toModel(MenuBO.class , param);
		
		RoleMenuBO roleMenuBO = new RoleMenuBO();
		roleMenuBO.setMenuId(menuBO.getMenuId());
		// 删除菜单时同时删除菜单关系表数据
		roleMenuService.deleteObjectByMenuId(roleMenuBO);
		menuService.deleteObject(menuBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======MenuAction--findOneMenu======>");
		
		MenuBO menuBO = BaseDto.toModel(MenuBO.class , param);
		return menuService.findOne(menuBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======MenuAction--findAllMenu======>");
				
		return menuService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======MenuAction--queryMenuForPage======>");
		
		return menuService.queryForPage(currentPage);
	}

	/**
     * 查询所有目录树数据
     */
	public List<Dto> findAllMenuTreeMap(Dto param) {
		logger.debug("<======MenuAction--findAllMenuTreeMap======>");
		return menuService.findAllMenuTreeMap(param);
	}

	/**
     * 查询所有目录树叶子节点和父节点
     */
    public List<Dto> findAllMenuTreeLeafMap(Dto param) {
    	logger.debug("<======MenuAction--findAllMenuTreeLeafMap======>");
		return menuService.findAllMenuTreeLeafMap(param);
    } 

	/**
	 * 批量处理按钮信息<br/>
	 * @param param
	 * @return
	 */
    public void bachDealButtons(Dto param) {
    	logger.debug("<======MenuAction--bachDealButtons======>");
		menuService.bachDealButtons(param);
    } 
    
	/**
	 * 获取指定用户页面按钮信息<br/>
	 * @param param
	 * @return
	 */
    public List<Dto> getbtnAccess(Dto param) {
    	logger.debug("<======MenuAction--getbtnAccess======>");
		return menuService.getbtnAccess(param);
    }
    
    /**
	 * 获取指定菜单的菜单树信息<br/>
	 * @param param
	 * @return
	 */
	public List<MenuTree> getMenuTree(String menuId, String state,
	        String currId,String parentIcon, String childIcon) {
		logger.debug("<======MenuAction--getMenuTree======>");
		return menuService.getMenuTree(menuId, state, currId, parentIcon, childIcon);
	}
	
	public List<Dto> findAllMenuTreeMap() {
		logger.debug("<======MenuAction--findAllMenuTreeMap======>");
		return menuService.findAllMenuTreeMap(new BaseDto());
	}
	
}
