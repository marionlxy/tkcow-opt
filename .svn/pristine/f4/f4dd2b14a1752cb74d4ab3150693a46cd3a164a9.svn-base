package com.taikang.udp.sys.service.impl;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.service.IUserService;
 
  
/**
  * UserServiceImpl
  */
 @Service(IUserService.SERVICE_ID)
 public class UserServiceImpl extends BaseServiceImpl 
 implements IUserService<UserBO>  
  {

	/**
	  * 增加数据
	  */
	public void insertObject(UserBO user) {
		logger.debug("<======UserServiceImpl--insertUser======>");
		/*UserBO queryuser = new UserBO();
		
		List<UserBO> userLst = this.findAll(queryuser);
		if (userLst != null && userLst.size() >=1) {
			throw new TKBizException("err", this.getClass().getName(), "insertObject",
			        "该用户编码:" + "" + "已经存在！", null);
		}
		SequenceGenerator generator = SequenceGenerator.getInstance("User_Id",11);
		long nextid = generator.nextId();*/
		/*user.setUserId(Integer.valueOf(String.valueOf(nextid)));*/
		appDao.insert("User.addUser",user);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(UserBO user) {
		logger.debug("<======UserServiceImpl--updateUser======>");
		appDao.update("User.updateUser",user);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(UserBO user) {
		logger.debug("<======UserServiceImpl--deleteUser======>");
		appDao.delete("User.deleteUser",user);
	}
	
	/**
      * 查询数据
      */
	public UserBO findOne(UserBO user) {
		logger.debug("<======UserServiceImpl--findUser======>");
		
		UserBO userBackBO=appDao.queryOneObject("User.findOneUser", user);
		return userBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<UserBO> findAll(UserBO  user) {
		logger.debug("<======UserServiceImpl--findAllUser======>");
		return appDao.queryForEntityList("User.findAllUser", user);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======UserServiceImpl--queryUserForPage======>");
		return appDao.queryForPage("User.queryUserForPage", currentPage);
	}

	 /**
     * 分页查询数据
     */
	public CurrentPage queryByCondition(CurrentPage currentPage) {
		logger.debug("<======UserServiceImpl--queryUserForPage======>");
		return appDao.queryForPage("user_deal.queryByCondition", currentPage);
	}

	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======UserServiceImpl--findAllMapUser======>");		
		return appDao.queryForMapList("User.findAllMapUser", param);
	}

	@Override
	public List<UserBO> verifyAlreadyExists(UserBO user) {
		return appDao.queryForEntityList("User.verifyAlreadyExists", user);
	}
 }
  