package com.taikang.udp.security.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.taikang.udp.sys.model.ResBO;
import com.taikang.udp.sys.model.RoleBO;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.service.IResService;
import com.taikang.udp.sys.service.IRoleService;
import com.taikang.udp.sys.service.IUserService;

public class SecurityManagerSupport implements UserDetailsService, SecurityManager {

	@Resource(name = IResService.SERVICE_ID)
	private IResService<ResBO> resService;

	@Resource(name = IRoleService.SERVICE_ID)
	private IRoleService<RoleBO> roleService;

	@Resource(name = IUserService.SERVICE_ID)
	private IUserService<UserBO> userService;

	@Override
	public Map<String, String> loadUrlAuthorities() {

		Map<String, String> urlAuthorities = new HashMap<String, String>();
		ResBO _in = new ResBO();
		_in.setType("URL");
		List<ResBO> urlResources = this.resService.findAll(_in);

		RoleBO _rbo = new RoleBO();

		for (ResBO res : urlResources) {
			_rbo.setResId(res.getId());
			List<RoleBO> roleList = roleService.listByResId(_rbo);
			res.setRoles(roleList);
			urlAuthorities.put(res.getValue(), res.getRoleAuthorities());
		}
		return urlAuthorities;
	}

	@Override
	public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
		String userName = data;
		UserBO user = new UserBO();
		user.setUsername(userName);
		List<UserBO> users = userService.findAll(user);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("User " + userName + " has no GrantedAuthority");
		}
		user = users.get(0);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		RoleBO roleBO = new RoleBO();
		roleBO.setUserId(user.getId());
		List<RoleBO> roleList = roleService.listByUserId(roleBO);
		Set<RoleBO> roleSet = new HashSet<RoleBO>();
		for (RoleBO role : roleList) {
			roleSet.add(role);
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRolecode().toUpperCase());
			authorities.add(grantedAuthority);
		}
		user.setAuthorities(authorities);
		user.setRoles(roleSet);
		return user;
	}

}
