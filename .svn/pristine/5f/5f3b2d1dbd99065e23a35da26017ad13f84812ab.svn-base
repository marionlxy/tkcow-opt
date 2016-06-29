package com.taikang.udp.sys.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.taikang.udp.framework.common.util.StringTokenizerUtils;
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.udp.framework.core.properties.PropertiesFile;
import com.taikang.udp.framework.core.properties.PropertiesHandler;
import com.taikang.udp.framework.core.properties.PropertiesHandlerFactory;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.service.IFileloadService;

@Service(IFileloadService.SERVICE_ID)
public class FileloadServiceImpl extends BaseServiceImpl implements
		IFileloadService {

	private ArrayList<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();
	
	
	  protected static PropertiesHandler initProperty = PropertiesHandlerFactory.getPropertiesHelper(PropertiesFile.FRAMEWORK);
		
		public static String SERVICE_BASEURL = initProperty.getValue("service_baseUrl");
		public static String BAK_UPLOAD_FILEPATH = initProperty.getValue("bak_upload_filepath");
	
	
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
	
	@Override
	public String uploadBySpringGrpFixed(HttpServletRequest request,
			String upLoadPath, String url, String string2) throws TKCheckedException {
		
		String FILE_SPLIT="\\";
		String bak_file_path=BAK_UPLOAD_FILEPATH;
		logger.info("开始文件上传操作！");
		
		String basePath = request.getContextPath();// 
		
		basePath = SERVICE_BASEURL+basePath+"/"; 
		String realPath=request.getRealPath("");//D:\apache-tomcat-6.0.35\webapps\mwp\
		
		if(realPath.contains("/")){
			FILE_SPLIT="/";
		}else if(realPath.contains("\\")){
			FILE_SPLIT="\\";
		}
		
		realPath=realPath+FILE_SPLIT;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		StringBuffer pictureUrl = new StringBuffer();
		String imgUrl = "";
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
		String upPath = fmt.format(new Date());// 201512
//		upLoadPath = upLoadPath+ url+"/" +upPath+"/";// /upload/application/201512/
		String url_1="static"+FILE_SPLIT+"appImages";
		String url_2="static"+FILE_SPLIT+"upload";		
		
		String url_01="static/appImages";
		String url_02="static/upload";
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
					String myFileName = file.getOriginalFilename();// 图片名
					
					if(myFileName!=null&&!"".equals(myFileName)){
						
						
						String [] str = StringTokenizerUtils.split(myFileName, ".");
//					System.out.println(str[0]);
//					System.out.println(str[1]);
//					
//					System.out.println(str[1].toUpperCase() == "JPEG");
//					System.out.println(str[1].toUpperCase().equals("JPEG"));
						
						if(str[1].toUpperCase().equals("JPEG") || str[1].toUpperCase().equals("JPG")
								|| str[1].toUpperCase().equals("PNG") || str[1].toUpperCase().equals("BMP")
								|| str[1].toUpperCase().equals("GIF") ){
							if (myFileName.trim() != "") {
								logger.info("上传文件：" + myFileName);
								// 重命名上传后的文件名
								String fileName = file.getOriginalFilename();
								if (!TKStringUtils.isEmpty(myFileName)) {
									fileName = myFileName;
								} else {
									fileName = url+ UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."),fileName.length());
								}
								upLoadPath = realPath+ url_1+FILE_SPLIT;// D:\apache-tomcat-6.0.35\webapps\mwp\static\appImages
								System.out.println("图片存放文件路径： "+upLoadPath);
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
										upLoadPath = upLoadPath + UUID.randomUUID(); //D:\eclipse\workspace\mwp\target\mwp\static\appImages
										path = upLoadPath + FILE_SPLIT+ fileName;
										File dir = new File(path);
										if (!dir.exists()) {
											if(!dir.mkdirs()) {
												throw new TKCheckedException("文件上传出错：文件路径配置错误！");
											}
										}
										localFile = new File(path);
									}
									
									//本地存一份
									if(TKStringUtils.isNotBlank(bak_file_path)){
										bak_file_path=bak_file_path.replace("/", FILE_SPLIT).replace("\\", FILE_SPLIT) + fileName;
										FileUtils.copyInputStreamToFile(file.getInputStream(), new File(bak_file_path));
									}
									
									
									file.transferTo(localFile);
									
									
//									localFile.
//							pictureUrl.append(url+"/" +upPath+"/"+fileName + ",");
									pictureUrl.append(basePath+url_01+"/"+fileName + ",");
									System.out.println("下载路径 （存到数据库字段中的值） ："+pictureUrl);
								} catch (IllegalStateException e) {
									throw new TKCheckedException("文件上传出错：文件状态错误！");
								} catch (IOException e) {
									throw new TKCheckedException("文件上传出错：文件流读取出错！");
								}
							}
						} else {
							
							if (myFileName.trim() != "") {
								logger.info("上传文件：" + myFileName);
								// 重命名上传后的文件名
								String fileName = file.getOriginalFilename();
								if (!TKStringUtils.isEmpty(myFileName)) {
									fileName = myFileName;
//									if (!TKStringUtils.isEmpty(upLoadfileName)) {
//										fileName = upLoadfileName;
								} else {
									fileName = url+ UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."),fileName.length());
								}
								upLoadPath = realPath+ url_2+FILE_SPLIT;// D:\apache-tomcat-6.0.35\webapps\mwp\static\appImages
								System.out.println("程序包路径:   "+upLoadPath);
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
										upLoadPath = upLoadPath + UUID.randomUUID(); //D:\eclipse\workspace\mwp\target\mwp\static\appImages
										path = upLoadPath + FILE_SPLIT+ fileName;
										File dir = new File(path);
										if (!dir.exists()) {
											if(!dir.mkdirs()) {
												throw new TKCheckedException("文件上传出错：文件路径配置错误！");
											}
										}
										localFile = new File(path);
									}
									
									
									if(TKStringUtils.isNotBlank(bak_file_path)){
										bak_file_path=bak_file_path.replace("/", FILE_SPLIT).replace("\\", FILE_SPLIT) + fileName;
										FileUtils.copyInputStreamToFile(file.getInputStream(), new File(bak_file_path));
									}
									
									
									file.transferTo(localFile);
									
									
									
//							pictureUrl.append(url+"/" +upPath+"/"+fileName + ",");
									pictureUrl.append(basePath+url_02+"/"+fileName + ",");
									System.out.println("下载路径 （存到数据库字段中的值） ："+pictureUrl);
								} catch (IllegalStateException e) {
									throw new TKCheckedException("文件上传出错：文件状态错误！");
								} catch (IOException e) {
									throw new TKCheckedException("文件上传出错：文件流读取出错！");
								}
							}
							
						}
					}
					}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				logger.info("文件上传用时：" + (finaltime - pre));
			}
			if(pictureUrl.toString()!=null && !"".equals(pictureUrl.toString())){
				imgUrl = pictureUrl.substring(0, pictureUrl.toString().length()-1);
			}
		}
		
		
		return imgUrl;
	}

}