package com.taikang.udp.sys.action;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.web.context.AppUser;
import com.taikang.udp.sys.util.vo.SysUserMenu;

/**
  * ILoginAction，管理系统登陆时使用到的业务服务
  */

public interface ILoginAction { 

	final String ACTION_ID = "loginAction"; 
	
	/**
	 * 根据登录用户信息查询数据库中的信息，并返回AppUser
	 * @param dto
	 * @return
	 */
	public AppUser checkUserByUserInfo(Dto param);
	
	/**
	 * 根据UserID更新用户的本地登陆信息
	 * @param param
	 */
	public void updateUserLoginInfo(AppUser param);
	
	/**
	 * 查询用户菜单信息
	 * @param userId
	 * @return
	 */
	public List<SysUserMenu> queryMenuListByUser(Dto param);
}
