package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IUserRoleAction;
import com.taikang.udp.sys.model.UserRoleBO;
import com.taikang.udp.sys.service.IUserRoleService;

/**
  * UserRoleAction
  */
  @Service(IUserRoleAction.ACTION_ID)
public class UserRoleActionImpl extends BaseActionImpl 
  implements IUserRoleAction {

    /**
      * 注入service
      */
    @Resource(name=IUserRoleService.SERVICE_ID)
	private IUserRoleService<UserRoleBO> userRoleService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======UserRoleAction--addUserRole======>");
		
		UserRoleBO userRoleBO = BaseDto.toModel(UserRoleBO.class , param);
		userRoleService.insertObject(userRoleBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======UserRoleAction--updateUserRole======>");
		
		UserRoleBO userRoleBO = BaseDto.toModel(UserRoleBO.class , param);
		userRoleService.updateObject(userRoleBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======UserRoleAction--deleteUserRole======>");
		
		UserRoleBO userRoleBO = BaseDto.toModel(UserRoleBO.class , param);
		userRoleService.deleteObject(userRoleBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======UserRoleAction--findOneUserRole======>");
		
		UserRoleBO userRoleBO = BaseDto.toModel(UserRoleBO.class , param);
		return userRoleService.findOne(userRoleBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======UserRoleAction--findAllUserRole======>");
				
		return userRoleService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======UserRoleAction--queryUserRoleForPage======>");
		
		return userRoleService.queryForPage(currentPage);
	}
	/**
	 * 批量保存用户角色信息<br/>
	 * @param param   
	 * void
	 */
	public void batchSaveUserRole(Dto param) {
		logger.debug("<======UserRoleAction--batchSaveUserRole======>");
		
		userRoleService.batchSaveUserRole(param);
	}
}
