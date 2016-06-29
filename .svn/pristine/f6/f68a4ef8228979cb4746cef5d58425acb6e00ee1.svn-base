package com.taikang.udp.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.IFileloadAction;

@Controller("uploadController")
@RequestMapping("/file")
public class UploadController extends BaseController {

	@Resource(name = "fileloadAction")
	private IFileloadAction fileloadAction;

	/**
	 * 打开上传页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showIndexPage() {
		return "fileupload/fileUpLoadIndex";
	}
	
	/**
	 * 打开上传页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("/ajaxfileUpLoad")
	public String showIndexAjaxfileUpLoadPage() {
		return "fileupload/AjaxfileUpLoadIndex";
	}
	
	/**
	 * springMVC包装好的解析器进行上传<br/>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadBySpringGrp")
	@ResponseBody
	public Map<String, String> uploadBySpringGrp(HttpServletRequest request,
			HttpServletResponse response) {
		
		String str1 = (String) request.getParameter("name");
		Dto dto = getParamAsDto(request);
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(!fileloadAction.uploadBySpringGrp(request)) {
				map.put(RTN_RESULT, "false");
				map.put(MESSAGE_INFO, "上传失败！");
				return map;
			}
		} catch (TKCheckedException e) {
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, "上传失败！");
			return map;
		}
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "上传成功！");
		return map;
	}

	
	/**
	 * 附件下载<br/>
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public Map<String, String> download(HttpServletRequest request,
			HttpServletResponse response, String filepathName,String file_name) {
		Map<String, String> map = new HashMap<String, String>();
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("设置请求编码错误！");
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, "操作失败！");
			return map;
		}
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String downLoadPath = filepathName;
		logger.info("下载文件路径" + downLoadPath);
		try {
			long fileLength = new File(filepathName).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(file_name.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filepathName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					logger.error("下载附件出错！");
					map.put(RTN_RESULT, "false");
					map.put(MESSAGE_INFO, "下载失败！");
					return map;
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("下载附件出错！");
					map.put(RTN_RESULT, "false");
					map.put(MESSAGE_INFO, "下载失败！");
					return map;
				}
		}
		return map;
	} 

}