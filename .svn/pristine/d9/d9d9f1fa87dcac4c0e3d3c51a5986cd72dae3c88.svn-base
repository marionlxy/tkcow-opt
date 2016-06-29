/**
 * 
 */
package com.taikang.udp.sys.util;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.taikang.udp.framework.common.util.TKConstants;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.properties.PropertiesHelper;
import com.taikang.udp.framework.core.web.context.AppUser;
import com.taikang.udp.framework.core.web.log.Log;
import com.taikang.udp.framework.core.web.log.LogAPI;
import com.taikang.udp.framework.core.web.log.LogLevel;
import com.taikang.udp.framework.core.web.log.LogMessageObject;
import com.taikang.udp.framework.core.web.log.impl.LogUitl;
import com.taikang.udp.sys.model.SysLogBO;
import com.taikang.udp.sys.util.enums.LogType;
import com.taikang.udp.sys.util.enums.YesNo;
import com.taikang.udp.sys.util.vo.LoginUser;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class); 
	
	private LogAPI logAPI;
	
	private AppUser user;
	
	/**   
	 * 将request存入LogUitl中的LOCAL_REQUEST。
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)  
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LogUitl.putRequest(request);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
	}
	
	/**
	 * 清除LogUitl中的LOCAL_REQUEST。
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)  
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return ;
		}
		
		//是否执行请求监视
		String eventMonitorEnabel = PropertiesHelper.isReqMonitor();
		if(eventMonitorEnabel.isEmpty() || eventMonitorEnabel.equals(String.valueOf(YesNo.No.getCode()))){//如果全局配置信息中没有开启请求监控，则直接跳出
			return ;
		}
		
		try{
			LoginUser loginUser = UserUtils.getUser();
			//(AppUser)request.getSession().getAttribute(TKConstants.SESSION_LOGIN_USER_KEY);
			user = new AppUser(); 
		/*	user.setUserId(String.valueOf(loginUser.getUserId()));*/
		/*	user.setUserCode(loginUser.getUserCode());
			user.setUserName(loginUser.getUserName());*/
			// user.setLoginIP(SecurityUtils.getSubject().getSession().getHost());
			user.setLoginIP(this.getRealIp(request));
		}catch(Exception e)
		{
			return;
		}
		
		if(user == null)
		{
			return;
		}
		
		StringBuilder params = new StringBuilder();
		int index = 0;
		for (Object param : request.getParameterMap().keySet()){ 
			params.append((index++ == 0 ? "" : "&") + param + "=");
			params.append(TKStringUtils.abbr(StringUtils.endsWithIgnoreCase((String)param, "password")
					? "" : request.getParameter((String)param), 100));
		}
		
		SysLogBO logBO = new SysLogBO();
		logBO.setLogType(ex == null ? LogType.TYPE_ACCESS.getCode() : LogType.TYPE_EXCEPTION.getCode());
		logBO.setUserId(user.getUserId());
		logBO.setCreateTime(TKDateTimeUtils.getTodayTimeStamp());
		logBO.setClientIp(user.getLoginIP());
		logBO.setUserName(user.getUserName());
		logBO.setReqUrl(request.getRequestURI().substring(request.getContextPath().length()));
		logBO.setReqMethod(request.getMethod());
		logBO.setReqParam(params.toString());
				
		if(ex != null){//如果存在异常，则直接保存，不用解析Log注解输入的参数
			logBO.setMessage(ex != null ? ex.toString() : "");
			logAPI.log(logBO);
		}
		else{		
			final HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();
			
			final Log log = method.getAnnotation(Log.class);
			if (log != null) {
				// 得到LogMessageObject
				final LogMessageObject logMessageObject = LogUitl.getArgs();
				
				try {
					LogLevel lastLogLevel = logAPI.getRootLogLevel();
					
					// 先对自定义包等级做判断
					Map<String, LogLevel> customLogLevel = logAPI.getCustomLogLevel();
					if (!customLogLevel.isEmpty()) {
						Class<?> clazz = handlerMethod.getBean().getClass();
						String packageName = clazz.getPackage().getName();
						
						Set<String> keys = customLogLevel.keySet();
						for (String key : keys) {
							if (packageName.startsWith(key)) {
								lastLogLevel = customLogLevel.get(key);
								break;
							}
						}
					}
					
					LogMessageObject defaultLogMessageObject = logMessageObject;
					if (defaultLogMessageObject == null) {
						defaultLogMessageObject = LogMessageObject.newWrite(); 
					}
					
					if (defaultLogMessageObject.isWritten()) { // 判断是否写入log						

						MessageFormat mFormat = new MessageFormat(log.message());
						String result = mFormat.format(defaultLogMessageObject.getObjects());
						
						if (!StringUtils.isNotBlank(result)) {
							result = "";
						}
						
						logBO.setMessage(result);
						logBO.setLogLevel(log.level().value());
						logBO.setOperType(log.operType().value());
						logBO.setOperName(log.operName());
						// 覆盖，直接写入日志
						if (log.override()) {
							
							logAPI.log(logBO);
						} else {
							// 不覆盖，参考方法的日志等级是否大于等于最终的日志等级
							if (!log.override() && log.level().compareTo(lastLogLevel) >= 0 ) {
								logAPI.log(logBO);
							}
						}
					}						
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage()+"("+e.getStackTrace().toString()+")");
				} 
			}
			else{
				String requestRri = request.getRequestURI();
				String uriPrefix = request.getContextPath() ;
				
				if ((StringUtils.startsWith(requestRri, uriPrefix) && (StringUtils.endsWith(requestRri, "/save")
						|| StringUtils.endsWith(requestRri, "/delete") || StringUtils.endsWith(requestRri, "/import")
						|| StringUtils.endsWith(requestRri, "/insert") || StringUtils.endsWith(requestRri, "/update")))) {
					
					logAPI.log(logBO);
				}
			}
		}
		
		LogUitl.removeRequest();
	}
	
	/**
	 * 获取请求源ip<br/>
	 * @param req
	 * @return
	 */
	public String getRealIp(HttpServletRequest req) {

		String ip = req.getHeader("X-Forwarded-For");
		if (ip != null) {
			if (ip.indexOf(',') == -1) {
				return ip;
			}
			return ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteHost();
		}
		return ip;
	}

	public void setLogAPI(LogAPI logAPI) {
		this.logAPI = logAPI;
	}
}
