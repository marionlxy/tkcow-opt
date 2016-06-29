package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IRoleAction;
import com.taikang.udp.sys.model.RoleBO;
import com.taikang.udp.sys.model.RoleMenuBO;
import com.taikang.udp.sys.model.UserRoleBO;
import com.taikang.udp.sys.service.IRoleMenuService;
import com.taikang.udp.sys.service.IRoleService;
import com.taikang.udp.sys.service.IUserRoleService;

/**
 * 
 *  处理角色相关<br/>
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2014年12月24日 下午5:38:24
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
  @Service(IRoleAction.ACTION_ID)
public class RoleActionImpl extends BaseActionImpl 
  implements IRoleAction {

    /**
     * 注入service
     */
    @Resource(name=IRoleService.SERVICE_ID)
	private IRoleService<RoleBO> roleService;	
    
    @Resource(name=IRoleMenuService.SERVICE_ID)
	private IRoleMenuService<RoleMenuBO> roleMenuService;
    
    @Resource(name=IUserRoleService.SERVICE_ID)
	private IUserRoleService<UserRoleBO> userRoleService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======RoleAction--addRole======>");
		
		RoleBO roleBO = BaseDto.toModel(RoleBO.class , param);
		roleService.insertObject(roleBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======RoleAction--updateRole======>");
		
		RoleBO roleBO = BaseDto.toModel(RoleBO.class , param);
		roleService.updateObject(roleBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======RoleAction--deleteRole======>");
		RoleBO roleBO = BaseDto.toModel(RoleBO.class , param);
		UserRoleBO userRole = new UserRoleBO ();
		/*userRole.setId(roleBO.getId());*/
		List<UserRoleBO> userRoleList = userRoleService.findAll(userRole);
		if (userRoleList != null && userRoleList.size()>=1) {
			throw new TKBizException("err", this.getClass().getName(), "deleteObject",
			        "该角色:已被赋予用户！", null);
		}
		RoleMenuBO roleMenuBO = new RoleMenuBO();
		/*roleMenuBO.setRoleId(roleBO.getRoleId());*/
		// 删除角色时同时删除角色菜单表数据
		roleMenuService.deleteObject(roleMenuBO);
		roleService.deleteObject(roleBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======RoleAction--findOneRole======>");
		
		RoleBO roleBO = BaseDto.toModel(RoleBO.class , param);
		return roleService.findOne(roleBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======RoleAction--findAllRole======>");
				
		return roleService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======RoleAction--queryRoleForPage======>");
		
		return roleService.queryForPage(currentPage);
	}

	/**
	 * 查询指定用户是否拥有角色信息列表<br/>
	 * @param 
	 * @return
	 * @throws TKBizException
	 */
    public List<Dto> getUsersRoleList(Dto param) throws TKBizException {
    	logger.debug("<======RoleAction--getUsersRoleList======>");
		/*return roleService.getUsersRoleList(param);*/
    	return null;
    }	
}
