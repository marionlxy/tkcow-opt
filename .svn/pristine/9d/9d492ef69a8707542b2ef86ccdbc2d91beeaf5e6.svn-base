package com.taikang.udp.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.taikang.udp.common.util.CommUtil;

public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

	private RequestMatcher urlMatcher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#getAttributes(
	 * java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {

		FilterInvocation filterInvocation = (FilterInvocation) filter;
		String requestURI = filterInvocation.getRequestUrl();
		boolean verify = true;
		if (verify && requestURI.indexOf("login.htm") < 0) {
			Map<String, String> urlAuthorities = this.getUrlAuthorities(filterInvocation);
			String grantedAuthorities = null;
			for (Iterator<Map.Entry<String, String>> iter = urlAuthorities.entrySet().iterator(); iter.hasNext();) {
				Map.Entry<String, String> entry = iter.next();
				String url = entry.getKey();
				urlMatcher = new AntPathRequestMatcher(url);
				if (!CommUtil.null2String(url).equals("") && urlMatcher.matches(filterInvocation.getHttpRequest())) {
					grantedAuthorities = entry.getValue();
					break;
				}
			}
			if (grantedAuthorities != null) {
				Collection<ConfigAttribute> configAttributeList = new ArrayList<ConfigAttribute>();
				String[] roles = grantedAuthorities.split(",");
				for (String role : roles) {
					ConfigAttribute config = new SecurityConfig(role);
					configAttributeList.add(config);
				}
				return configAttributeList;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.access.SecurityMetadataSource#
	 * getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#supports(java.
	 * lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return true;
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getUrlAuthorities(FilterInvocation filterInvocation) {
		ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
		return (Map<String, String>) servletContext.getAttribute("urlAuthorities");
	}

}