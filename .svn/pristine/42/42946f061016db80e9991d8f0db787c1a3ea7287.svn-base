package com.taikang.udp.security.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


public class OptSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        //登录成功后清除验证码
        request.getSession(false).removeAttribute("verify_code");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
