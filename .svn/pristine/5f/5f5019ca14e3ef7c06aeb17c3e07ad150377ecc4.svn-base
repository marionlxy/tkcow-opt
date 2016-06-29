package com.taikang.udp.sys.service.impl;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.RoleMenuBO;
import com.taikang.udp.sys.service.IRoleMenuService;
 
  
/**
  * RoleMenuServiceImpl
  */
 @Service(IRoleMenuService.SERVICE_ID)
 public class RoleMenuServiceImpl extends BaseServiceImpl 
 implements IRoleMenuService<RoleMenuBO>  
  {
  	
 	/**
	  * 增加数据
	  */
	public void insertObject(RoleMenuBO roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--insertRoleMenu======>");		
		appDao.insert("RoleMenu.addRoleMenu",roleMenu);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(RoleMenuBO roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--updateRoleMenu======>");
		appDao.update("RoleMenu.updateRoleMenu",roleMenu);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(RoleMenuBO roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--deleteRoleMenu======>");
		appDao.delete("RoleMenu.deleteRoleMenu",roleMenu);
	}

	 /**
     * 删除数据
     */
	public void deleteObjectByMenuId(RoleMenuBO roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--deleteRoleMenu======>");
		appDao.delete("RoleMenu.deleteRoleMenuByMenuId",roleMenu);
	}

	/**
      * 查询数据
      */
	public RoleMenuBO findOne(RoleMenuBO roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--findRoleMenu======>");
		
		RoleMenuBO roleMenuBackBO=appDao.queryOneObject("RoleMenu.findOneRoleMenu", roleMenu);
		return roleMenuBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<RoleMenuBO> findAll(RoleMenuBO  roleMenu) {
		logger.debug("<======RoleMenuServiceImpl--findAllRoleMenu======>");
		return appDao.queryForEntityList("RoleMenu.findAllRoleMenu", roleMenu);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======RoleMenuServiceImpl--queryRoleMenuForPage======>");
		return appDao.queryForPage("RoleMenu.queryRoleMenuForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======RoleMenuServiceImpl--findAllMapRoleMenu======>");		
		return appDao.queryForMapList("RoleMenu.findAllMapRoleMenu", param);
	}

	/**
	 * 批量处理用户角色菜单设置<br/>
	 * @param roleId
	 * @param loginuser
	 * @param menuIdList
	 * @return
	 */
    @Override
    public boolean batchSaveRoleMenu(String roleId, String loginuser,String menuIdList) {
    	
	    // 删除该角色下角色目录关系
	    RoleMenuBO roleMenu = new RoleMenuBO();
	    roleMenu.setRoleId(Integer.valueOf(roleId));
	    appDao.delete("RoleMenu.deleteRoleMenu",roleMenu);
	    List<RoleMenuBO> list = new ArrayList<RoleMenuBO>();
	    // RoleMenuBO roleMenuIn = null;
	    
	    String[] array = menuIdList.split("_");
	    // 批量增加用户角色菜单
	    for(String menuId : array){
	    	RoleMenuBO roleMenuIn = new RoleMenuBO();
	    	roleMenuIn.setRoleId(Integer.valueOf(roleId));
	    	roleMenuIn.setMenuId(Integer.valueOf(menuId));
	    	roleMenuIn.setCreateTime(TKDateTimeUtils.getTodayTimeStamp());
	    	roleMenuIn.setCreator(Integer.valueOf(loginuser));
	    	roleMenuIn.setLastModtime(TKDateTimeUtils.getTodayTimeStamp());
	    	roleMenuIn.setLastModby(Integer.valueOf(loginuser));
	    	list.add(roleMenuIn);
	    	//this.insertObject(roleMenu);
		}
	    appDao.batchSaveByBO("RoleMenu.addRoleMenu", list);
	    return true;
    }
    
    /**
	public List<RoleMenuBO> menuRoleLst = new  ArrayList<RoleMenuBO>();
	public Integer roleId;
    /**
     * 设置菜单角色list<br/>
     * @param roleMenuList
     */
    /**public void setMenuRoleList (List<Map> roleMenuList) {
    	RoleMenuBO roleMenuBO = null;
    	if (roleMenuList != null) {
    		for (int i=0; i<roleMenuList.size(); i++) {
    			roleMenuBO = new RoleMenuBO();
    			roleMenuBO.setMenuId((Integer) roleMenuList.get(i).get("Menu_Id"));
    			roleMenuBO.setRoleId(this.roleId);
    			menuRoleLst.add(roleMenuBO);
    			List<Map> childrenLstMap = (List<Map>) roleMenuList.get(i).get("children");
    			if (childrenLstMap == null) {
    				continue;
    			} else {
    				this.setMenuRoleList(childrenLstMap);
    			}
    		}
    	}
    }**/
 }
  