package com.taikang.udp.security.service;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.taikang.udp.sys.model.UserBO;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	public static final String SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY = "SPRING_SECURITY_403_EXCEPTION";
	private String errorPage;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		this.errorPage = "/authority.htm";
		UserBO user = SecurityUserHolder.getCurrentUser();
		Collection<? extends GrantedAuthority> all_authorities = user.get_all_Authorities();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> current_authorities = auth.getAuthorities();

		if (user.getUserrole().indexOf("ADMIN") < 0) {
			this.errorPage = "/buyer/authority.htm";
		} else {
			if (all_authorities.size() != current_authorities.size()) {
				this.errorPage = "/admin/login.htm";
			}
		}

		if (this.errorPage != null) {
			((HttpServletRequest) request).setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);

			RequestDispatcher rd = request.getRequestDispatcher(this.errorPage);
			rd.forward(request, response);
		} else {
			if (!response.isCommitted()) {
				((HttpServletResponse) response).sendError(403, accessDeniedException.getMessage());
			}
		}
	}

	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && (!errorPage.startsWith("/"))) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}

}
