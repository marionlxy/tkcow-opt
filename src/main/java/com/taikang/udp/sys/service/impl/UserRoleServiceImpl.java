package com.taikang.udp.sys.service.impl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.UserRoleBO;
import com.taikang.udp.sys.service.IUserRoleService;
 
  
/**
  * UserRoleServiceImpl
  */
 @Service(IUserRoleService.SERVICE_ID)
 public class UserRoleServiceImpl extends BaseServiceImpl 
 implements IUserRoleService<UserRoleBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(UserRoleBO userRole) {
		logger.debug("<======UserRoleServiceImpl--insertUserRole======>");		
		appDao.insert("UserRole.addUserRole",userRole);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(UserRoleBO userRole) {
		logger.debug("<======UserRoleServiceImpl--updateUserRole======>");
		appDao.update("UserRole.updateUserRole",userRole);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(UserRoleBO userRole) {
		logger.debug("<======UserRoleServiceImpl--deleteUserRole======>");
		appDao.delete("UserRole.deleteUserRole",userRole);
	}
	
	/**
      * 查询数据
      */
	public UserRoleBO findOne(UserRoleBO userRole) {
		logger.debug("<======UserRoleServiceImpl--findUserRole======>");
		
		UserRoleBO userRoleBackBO=appDao.queryOneObject("UserRole.findOneUserRole", userRole);
		return userRoleBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<UserRoleBO> findAll(UserRoleBO  userRole) {
		logger.debug("<======UserRoleServiceImpl--findAllUserRole======>");
		return appDao.queryForEntityList("UserRole.findAllUserRole", userRole);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======UserRoleServiceImpl--queryUserRoleForPage======>");
		return appDao.queryForPage("UserRole.queryUserRoleForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======UserRoleServiceImpl--findAllMapUserRole======>");		
		return appDao.queryForMapList("UserRole.findAllMapUserRole", param);
	}
	
	/**
	 * 批量保存用户角色信息<br/>
	 * @param param   
	 * void
	 */
	public void batchSaveUserRole(Dto param){
		logger.debug("<======UserRoleServiceImpl--batchSaveUserRole======>");

		List<Map> roleMenus = (List<Map>) param.get("roleMenus");
		Integer userId = Integer.valueOf(String.valueOf(param.get("userCode")));
		Integer loginId = Integer.valueOf(String.valueOf(param.get("loginId")));
		
		Integer roleId;
		List<UserRoleBO> boList = new ArrayList <UserRoleBO>();
		if (roleMenus != null && roleMenus.size() > 0) {
			UserRoleBO userRoleBO = null;
			for ( Map roleMenu: roleMenus) {
				userRoleBO = new UserRoleBO();
				roleId = Integer.valueOf(String.valueOf(roleMenu.get("Role_Id")));
				userRoleBO.setRoleId(roleId);
				userRoleBO.setUserId(userId);
				userRoleBO.setCreator(loginId);
				userRoleBO.setCreateTime(TKDateTimeUtils.getTodayTimeStamp());
				userRoleBO.setLastModby(loginId);
				userRoleBO.setLastModtime(TKDateTimeUtils.getTodayTimeStamp());
				boList.add(userRoleBO);  
			}
		}
		UserRoleBO userRole = new UserRoleBO();
		userRole.setUserId(userId);
		// 先删除再批量保存
		this.deleteObject(userRole);
		appDao.batchSaveByBO("UserRole.addUserRole", boList);
	}
 }
  