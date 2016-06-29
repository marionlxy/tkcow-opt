package com.taikang.udp.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.sys.util.servlet.ValidateCodeServlet;

public class OptLoginFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String requestCaptcha = request.getParameter(ValidateCodeServlet.VALIDATE_CODE);
		if (requestCaptcha != null) {
			requestCaptcha = CommUtil.getFromBase64(requestCaptcha).toUpperCase();
		}
		String genCaptcha = (String) request.getSession().getAttribute(ValidateCodeServlet.VALIDATE_CODE);

		logger.info("开始校验验证码，生成的验证码为：" + genCaptcha + " ，输入的验证码为：" + requestCaptcha);

		if (!genCaptcha.equals(requestCaptcha)) {
			throw new BadCredentialsException("验证码错误!");
		}

		String username = obtainUsername(request);
		if (username != null) {
			username = CommUtil.getFromBase64(username);
		}
		if (username == null || username.trim().length() == 0) {
			throw new BadCredentialsException("请输入用户名!");
		}
		String password = obtainPassword(request);
		if (password != null) {
			password = CommUtil.getFromBase64(password);
		}
		if (password == null || password.trim().length() == 0) {
			throw new BadCredentialsException("请输入密码!");
		}
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username.trim(),
				password);
		HttpSession session = request.getSession();
		if ((session != null) || (getAllowSessionCreation())) {
			request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",
					TextEscapeUtils.escapeEntities(username));
		}
		setDetails(request, authRequest);
		return getAuthenticationManager().authenticate(authRequest);
	}
}
