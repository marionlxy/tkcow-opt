package com.taikang.udp.sys.action;

import java.util.List;

import com.taikang.udp.sys.model.MenuBO;
import com.taikang.udp.sys.model.RoleBO;
import com.taikang.udp.sys.util.vo.LoginUser;
import com.taikang.udp.sys.util.vo.SysUserMenu;

/**
  * ISystemService，管理系统登陆时使用到的业务服务
  */

public interface ISystemAction { 

	final String SERVICE_ID = "systemAction"; 
	
	public LoginUser getUser(int userId);
	
	public LoginUser getUserByLoginName(String loginName);
	
	public void saveUser(LoginUser user);
	
	public void updatePasswordById(int userId,String loginName, String newPassword);
	
	public void updateUserLoginInfo(int userId);
	
	public RoleBO getRole(int roleId);
	
	public List<RoleBO> findAllRole();
	
	public List<LoginUser> findAllUser();
	
	public void saveRole(RoleBO role);
	
	public void deleteRole(int roleId);
	
	public Boolean outUserInRole(RoleBO role, int userId) ;
	
	public LoginUser assignUserToRole(RoleBO role, int userId);
	
	public MenuBO getMenu(int menuId);
	
	public List<SysUserMenu> findAllMenu();
	
	public void saveMenu(MenuBO menu);
	
	public void deleteMenu(int menuId);
}
