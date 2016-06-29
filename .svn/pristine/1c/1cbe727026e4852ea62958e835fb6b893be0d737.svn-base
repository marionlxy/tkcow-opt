package com.taikang.udp.common.listener;

import javax.servlet.ServletContextListener;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import com.taikang.udp.security.service.SecurityManager;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().removeAttribute(
				"urlAuthorities");
		
	}
	
	
	protected SecurityManager getSecurityManager(ServletContext servletContext) {
		return (SecurityManager) WebApplicationContextUtils
				.getWebApplicationContext(servletContext).getBean(
						"securityManager");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		SecurityManager securityManager = this
				.getSecurityManager(servletContext);
		Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
		servletContext.setAttribute("urlAuthorities", urlAuthorities);
		
	}

}
