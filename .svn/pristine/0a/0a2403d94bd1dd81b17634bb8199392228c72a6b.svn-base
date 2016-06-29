package com.taikang.udp.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.service.ILoginService;
import com.taikang.udp.sys.util.enums.UserStatus;

/**
 * UserServiceImpl
 */
@Service(ILoginService.SERVICE_ID)
public class LoginServiceImpl extends BaseServiceImpl implements ILoginService {

	public UserBO checkUserByUserInfo(UserBO user) {

		UserBO userBack;
		try {
			userBack = appDao.queryOneObject("Login.checkUserByUserInfo", user);
		} catch (Exception e) {
			throw new TKBizException("", this.getClass().getName(),
			        "checkUserByUserInfo", "登陆账户异常，无法登陆，请联系管理员！", e);
		}

		if (userBack == null) {
			throw new TKBizException("", this.getClass().getName(),
			        "checkUserByUserInfo", "系统不存在此用户！", null);
		} else if (UserStatus.Locking.getCode().toString()
		        .equals("")) {
			throw new TKBizException("", this.getClass().getName(),
			        "checkUserByUserInfo", "该用户已锁定，请联系管理员！", null);
		} else if (UserStatus.Deleting.getCode().toString()
		        .equals("")
		        || UserStatus.Invalid.getCode().toString()
		                .equals("")) {
			throw new TKBizException("", this.getClass().getName(),
			        "checkUserByUserInfo", "该用户已失效或已删除，无法登陆！", null);
		}

		return userBack;
	}

	/**
	 * 更新登陆用户信息
	 */
	public void updateUserLoginInfo(UserBO user) {
		appDao.update("User.updateUser", user);
	}

	public List<Dto> queryMenuListByUser(Dto param) {
		// TODO Auto-generated method stub
		return appDao.queryForMapList("Login.findAllMenuByUserId", param);
	}

}
