package com.taikang.udp.sys.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.service.IFileloadService;

@Service(IFileloadService.SERVICE_ID)
public class FileloadServiceImpl extends BaseServiceImpl implements
		IFileloadService {

	private ArrayList<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();
	
	public List<HashMap<String, String>> getUpFileList() {
		return this.fileList;
	}
	
	
	/**
	 * springMVC包装好的解析器进行上传<br/>
	 * 
	 * @param request
	 * @return
	 * @throws TKCheckedException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean uploadBySpringGrp(HttpServletRequest request,
			String upLoadPath, String upLoadfileName) throws TKCheckedException {
		logger.info("开始文件上传操作！");
		// 解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			fileList = new ArrayList<HashMap<String, String>>();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				logger.info("文件上传开始时间：" + pre);
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					if (myFileName.trim() != "") {
						logger.info("上传文件：" + myFileName);
						// 重命名上传后的文件名
						String fileName = file.getOriginalFilename();
						if (!TKStringUtils.isEmpty(upLoadfileName)) {
							fileName = upLoadfileName;
						} else {
							fileName = file.getOriginalFilename();
						}
						File fileDir = new File(upLoadPath);
						if (!fileDir.exists()) {
							if(!fileDir.mkdirs()) {
								throw new TKCheckedException("文件上传出错：文件路径配置错误！");
							}
						}
						// 定义上传路径
						String path = upLoadPath + fileName;
						File localFile = new File(path);
						HashMap<String, String> map = new HashMap<String, String>();
						try {
							if (localFile.exists()) {
								upLoadPath = upLoadPath + UUID.randomUUID(); 
								path = upLoadPath + "//"+ fileName;
								File dir = new File(path);
								if (!dir.exists()) {
									if(!dir.mkdirs()) {
										throw new TKCheckedException("文件上传出错：文件路径配置错误！");
									}
								}
								localFile = new File(path);
							}
							file.transferTo(localFile);
							map.put("pathname", path);
							map.put("path", upLoadPath);
							map.put("filename", fileName);
							fileList.add(map);
						} catch (IllegalStateException e) {
							throw new TKCheckedException("文件上传出错：文件状态错误！");
						} catch (IOException e) {
							throw new TKCheckedException("文件上传出错：文件流读取出错！");
						}
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				logger.info("文件上传用时：" + (finaltime - pre));
			}

		}
		return true;
	}

}