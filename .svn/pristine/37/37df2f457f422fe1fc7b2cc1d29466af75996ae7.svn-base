package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IUserAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.model.UserRoleBO;
import com.taikang.udp.sys.service.IUserRoleService;
import com.taikang.udp.sys.service.IUserService;

/**
  * UserAction
  */
  @Service(IUserAction.ACTION_ID)
public class UserActionImpl extends BaseActionImpl 
  implements IUserAction {

    /**
      * 注入service
      */
    @Resource(name=IUserService.SERVICE_ID)
	private IUserService<UserBO> userService;	
	
    /**
     * 注入service
     */
    @Resource(name=IUserRoleService.SERVICE_ID)
	private IUserRoleService<UserRoleBO> userRoleService;	

	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======UserAction--addUser======>");
		
		UserBO userBO = BaseDto.toModel(UserBO.class , param);
		userService.insertObject(userBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======UserAction--updateUser======>");
		
		UserBO userBO = BaseDto.toModel(UserBO.class , param);
		userService.updateObject(userBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======UserAction--deleteUser======>");
		UserBO userBO = BaseDto.toModel(UserBO.class , param);
		UserRoleBO userRole = new UserRoleBO ();
		userRole.setUserId(1);
		// 删除用户时同时删除用户角色表数据
		userRoleService.deleteObject(userRole);
		userService.deleteObject(userBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======UserAction--findOneUser======>");
		
		UserBO userBO = BaseDto.toModel(UserBO.class , param);
		return userService.findOne(userBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======UserAction--findAllUser======>");
				
		return userService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======UserAction--queryUserForPage======>");
		
		return userService.queryForPage(currentPage);
	}	
	
	 /**
     * 按条件分页查询数据
     */
	public CurrentPage queryByCondition(CurrentPage currentPage){
		logger.debug("<======UserAction--queryUserForPage======>");
		return userService.queryByCondition(currentPage);
	}

	@Override
	public List<UserBO> findAll(UserBO param) throws TKBizException {
		logger.debug("<======UserAction--findAll======>");
		return userService.findAll(param);
	}

	@Override
	public List<UserBO> verifyAlreadyExists(UserBO user) {
		return userService.verifyAlreadyExists(user);
	}
}
