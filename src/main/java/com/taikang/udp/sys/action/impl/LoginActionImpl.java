package com.taikang.udp.sys.action.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.web.context.AppUser;
import com.taikang.udp.sys.action.ILoginAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.service.ILoginService;
import com.taikang.udp.sys.util.vo.SysUserMenu;

/**
  * LoginActionImpl
  */
  @Service(ILoginAction.ACTION_ID)
public class LoginActionImpl extends BaseActionImpl 
  implements ILoginAction {

    /**
      * 注入service
      */
    @Resource(name=ILoginService.SERVICE_ID)
	private ILoginService loginService;

	public AppUser checkUserByUserInfo(Dto param) {
		UserBO userBO = BaseDto.toModel(UserBO.class , param);
		//先判断参数是否为空
		if(userBO == null || userBO.getData() == null || userBO.getUsername() == null || "".equals(userBO.getUsername())){
			throw new TKBizException("",this.getClass().getName(),"checkUserByUserInfo","登陆用户名参数不能为空！",null);
		}
		UserBO userBackBO = loginService.checkUserByUserInfo(userBO);
		return userBO2AppUser(userBackBO);
	}

	public void updateUserLoginInfo(AppUser param) {
		UserBO user = new UserBO();
	/*	
		user.setUserId(Integer.valueOf(param.getUserId()));
		user.setIsLogin(param.getIsLogin());
		user.setErrNum(param.getErrNum());
		user.setLoginTime(TKDateTimeUtils.convertToTimestamp(param.getLoginTime()));
		user.setClientIp(param.getLoginIP());*/
		
		loginService.updateUserLoginInfo(user);
	}

	public List<SysUserMenu> queryMenuListByUser(Dto param) {
		List<Dto> menuDtoList = loginService.queryMenuListByUser(param);
		
		/** 将查询到的菜单信息转换为SysMenuMenu对象*/
		List<SysUserMenu> returnList = new ArrayList<SysUserMenu>();
		for (Dto oneMenu : menuDtoList) {
			SysUserMenu menu = new SysUserMenu();
			menu.setMenuId(String.valueOf(oneMenu.get("MENU_ID")));
			menu.setMenuName(String.valueOf(oneMenu.get("MENU_NAME")));
			menu.setParentId(String.valueOf(oneMenu.get("PARENT_ID")));
			menu.setParentName(String.valueOf( oneMenu.get("PARENT_NAME")));
			menu.setUrl(String.valueOf(oneMenu.get("URL")));
			returnList.add(menu);
		}
		return returnList;
	}	
	
	//从UserPO转为User
	private AppUser userBO2AppUser(UserBO userBO){
		AppUser user = new AppUser();
		/*user.setUserId(String.valueOf(userBO.getUserId()));
		user.setUserCode(userBO.getUserCode());
		user.setUserPwd(userBO.getUserPwd());
		user.setUserName(userBO.getUserName());
		user.setErrNum(userBO.getErrNum().intValue());
		user.setPwdLog(userBO.getPwdLog());
		user.setUserNature(userBO.getUserNature());
		user.setUserStatus(userBO.getUserStatus());
		//user.setLoginTime((userBO.getLoginTime());
		user.setIsLogin(userBO.getIsLogin());
		user.setLoginIP(userBO.getClientIp());*/
		
		return user;
	}	
}
