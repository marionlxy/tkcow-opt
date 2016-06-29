package com.taikang.tkcoww.com;

import java.io.InputStream;

import com.taikang.udp.framework.core.properties.PropertiesFile;
import com.taikang.udp.framework.core.properties.PropertiesHandler;
import com.taikang.udp.framework.core.properties.PropertiesHandlerFactory;


/**
 *TODO 读取密钥工具类
 *CommonUtil.java
 * @author itw_lixy02
 * @date 2015年12月11日上午10:17:27
 */
public class CommonUtil {
	
	protected static PropertiesHandler initProperty = PropertiesHandlerFactory.getPropertiesHelper(PropertiesFile.FRAMEWORK);
	
	public static String RELATION_UPLOAD_FILEPATH = initProperty.getValue("relation_upload_filepath");
    
	public static String uploadFilePath(){
		String defaultFilePath = "";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesHandlerFactory.class.getClassLoader();
		}
		// 加载属性文件global.eredbos.properties
		try {
			InputStream is = classLoader.getResourceAsStream("META-INF/app_config/properties/init.config.properties");
			if(is!=null){
				PropertiesHandler ph = new PropertiesHandler(is);
				defaultFilePath = ph.getValue("default_upload_filepath");
			}			
		} catch (Exception e1) {
		}
		return defaultFilePath;
	}
}