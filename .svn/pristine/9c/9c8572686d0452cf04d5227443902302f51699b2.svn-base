package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IRoleMenuAction;
import com.taikang.udp.sys.model.RoleMenuBO;
import com.taikang.udp.sys.service.IRoleMenuService;

/**
  * RoleMenuAction
  */
  @Service(IRoleMenuAction.ACTION_ID)
public class RoleMenuActionImpl extends BaseActionImpl 
  implements IRoleMenuAction {

    /**
      * 注入service
      */
    @Resource(name=IRoleMenuService.SERVICE_ID)
	private IRoleMenuService<RoleMenuBO> roleMenuService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======RoleMenuAction--addRoleMenu======>");
		
		RoleMenuBO roleMenuBO = BaseDto.toModel(RoleMenuBO.class , param);
		roleMenuService.insertObject(roleMenuBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======RoleMenuAction--updateRoleMenu======>");
		
		RoleMenuBO roleMenuBO = BaseDto.toModel(RoleMenuBO.class , param);
		roleMenuService.updateObject(roleMenuBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======RoleMenuAction--deleteRoleMenu======>");
		
		RoleMenuBO roleMenuBO = BaseDto.toModel(RoleMenuBO.class , param);
		roleMenuService.deleteObject(roleMenuBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======RoleMenuAction--findOneRoleMenu======>");
		
		RoleMenuBO roleMenuBO = BaseDto.toModel(RoleMenuBO.class , param);
		return roleMenuService.findOne(roleMenuBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======RoleMenuAction--findAllRoleMenu======>");
				
		return roleMenuService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======RoleMenuAction--queryRoleMenuForPage======>");
		
		return roleMenuService.queryForPage(currentPage);
	}

	/**
	 * 批量增加用户角色菜单配置<br>
	 */
    public boolean batchSaveRoleMenu(String roleId, String longinuser,String menuIdList) {
    	logger.debug("<======RoleMenuAction--batchSaveRoleMenu======>");
    	
	    return roleMenuService.batchSaveRoleMenu(roleId, longinuser, menuIdList);
    }	
}
