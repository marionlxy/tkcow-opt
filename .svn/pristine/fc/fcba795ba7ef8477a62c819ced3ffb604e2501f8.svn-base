package com.taikang.udp.sys.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.taikang.udp.framework.core.exception.TKCheckedException;

public interface IFileloadService {

	 
 	final String SERVICE_ID = "fileloadService";  	

	/**
	 * springMVC包装好的解析器进行上传<br/>
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean uploadBySpringGrp(HttpServletRequest request,
			String upLoadPath, String upLoadfileName) throws TKCheckedException;

	/**
	 * 获取上传成功文件信息<br/>
	 * @return
	 */
	public List<HashMap<String, String>> getUpFileList();
	
}