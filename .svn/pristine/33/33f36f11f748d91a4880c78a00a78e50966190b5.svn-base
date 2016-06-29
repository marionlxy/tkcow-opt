package com.taikang.udp.security.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.taikang.udp.sys.model.LogBO;
import com.taikang.udp.sys.model.UserBO;

public class OptLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session != null) {
			UserBO u = SecurityUserHolder.getCurrentUser();
			if (u != null) {
				LogBO log = new LogBO();

			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.htm");
		dispatcher.forward(request, response);
	}

}
