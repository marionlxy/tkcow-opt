package com.taikang.udp.sys.util.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.taikang.udp.sys.model.RoleBO;
import com.taikang.udp.sys.model.UserBO;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = -925350357539820864L;
	
	private UserBO user;
	
	private List<RoleBO> roleList = Lists.newArrayList(); // 拥有角色列表
	  
  	public LoginUser(UserBO user) {
		this.user = user;
	}
  	
  	public UserBO getUser(){
  		return this.user;
  	}
  	
  	
	/**
	 * 用户拥有的角色
	 * @return
	 */
	public List<RoleBO> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<RoleBO> roleList) {
		this.roleList = roleList;
	}

	public List<Long> getRoleIdList() {
		List<Long> roleIdList = Lists.newArrayList();
		for (RoleBO role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		roleList = Lists.newArrayList();
		for (Long roleId : roleIdList) {
			RoleBO role = new RoleBO();
			role.setId(roleId);
			roleList.add(role);
		}
	}
}
