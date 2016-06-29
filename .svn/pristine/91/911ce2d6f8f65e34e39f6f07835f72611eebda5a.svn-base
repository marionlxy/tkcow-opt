package com.taikang.udp.sys.service;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.sys.model.UserBO;

/**
  * ILoginAction，管理系统登陆时使用到的业务服务
  */

public interface ILoginService { 

	final String SERVICE_ID = "loginService"; 
	
	/**
	 * 根据登录用户信息查询数据库中的信息，返回UserBO
	 * @param dto
	 * @return
	 */
	public UserBO checkUserByUserInfo(UserBO user);
	
	/**
	 * 根据UserID更新用户的本地登陆信息
	 * @param param
	 */
	public void updateUserLoginInfo(UserBO user);
	
	/**
	 * 查询用户菜单信息
	 * @param userId
	 * @return
	 */
	public List<Dto> queryMenuListByUser(Dto param);
}
