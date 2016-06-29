package com.taikang.udp.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.taikang.udp.sys.model.UserBO;

public class SecurityUserHolder {

	public static UserBO getCurrentUser() {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserBO) {

			return (UserBO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			UserBO user = null;
			if (RequestContextHolder.getRequestAttributes() != null) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				user = (request.getSession().getAttribute("user") != null
						? (UserBO) request.getSession().getAttribute("user") : null);
			}
			return user;
		}
	}
}
