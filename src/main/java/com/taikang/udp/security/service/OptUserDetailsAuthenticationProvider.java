package com.taikang.udp.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class OptUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private HttpServletRequest request;

	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	private SaltSource saltSource;
	private UserDetailsService userDetailsService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String ip = request.getRemoteAddr();
		if (loginAttemptService.isBlocked(ip)) {
			throw new BadCredentialsException("blocked");
		}
		Object salt = null;
		if (this.saltSource != null) {
			salt = this.saltSource.getSalt(userDetails);
		}
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("登录验证失败，请输入正确的用户名和密码！");
		}
		String presentedPassword = authentication.getCredentials().toString();

		logger.info("开始校验密码。");
		if (!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
			throw new BadCredentialsException("登录验证失败，请输入正确的用户名和密码！");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;
		try {
			loadedUser = getUserDetailsService().loadUserByUsername(username);
		} catch (DataAccessException repositoryProblem) {
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new AuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}

		return loadedUser;
	}

	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	protected Md5PasswordEncoder getPasswordEncoder() {
		return this.passwordEncoder;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	protected SaltSource getSaltSource() {
		return this.saltSource;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}
}
