package com.taikang.udp.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.taikang.udp.sys.util.CommonUtil;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		return CommonUtil.xssEncode(super.getHeader(name));
	}

	@Override
	public String getQueryString() {
		return CommonUtil.xssEncode(super.getQueryString());
	}

	@Override
	public String getParameter(String name) {
		return CommonUtil.xssEncode(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = CommonUtil.xssEncode(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

}
