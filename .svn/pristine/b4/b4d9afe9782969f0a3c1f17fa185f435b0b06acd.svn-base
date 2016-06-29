package com.taikang.udp.sys.action.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.file.model.FilepathBO;
import com.taikang.udp.file.service.IFilepathService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.udp.sys.action.IFileloadAction;
import com.taikang.udp.sys.service.IFileloadService;
import com.taikang.udp.sys.util.sequence.SequenceGenerator;

@Service(IFileloadAction.ACTION_ID)
public class FileloadActionImpl extends BaseActionImpl implements
		IFileloadAction {

	/**
	 * 注入service
	 */
	@Resource(name = IFileloadService.SERVICE_ID)
	private IFileloadService fileloadService;
	
    /**
     * 注入service
     */
   @Resource(name=IFilepathService.SERVICE_ID)
	private IFilepathService<FilepathBO> filepathService;
	
	/**
	 * 注入默认上传文件保存路径
	 */
	public String upLoadPath = "";
	
	/**
	 * springMVC包装好的解析器进行上传<br/>
	 * 
	 * @param request
	 * @return
	 * @throws TKCheckedException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean uploadBySpringGrp(HttpServletRequest request)
			throws TKCheckedException {
		if (fileloadService.uploadBySpringGrp(request,upLoadPath,"")) {
			List<HashMap<String, String>> fileLst = fileloadService.getUpFileList();
			
			Iterator<HashMap<String, String>>  iter = fileLst.iterator();
			FilepathBO filepath = null;
			List<FilepathBO> filepathList = null;
			//LoginUser user = UserUtils.getUser();
			
			filepathList = new ArrayList<FilepathBO>();
			while (iter.hasNext()) {
				SequenceGenerator generator = SequenceGenerator.getInstance("FILEPATH_Id",11);
				long nextid = generator.nextId();
				filepath = new FilepathBO();
				HashMap<String, String> map = iter.next();
				filepath.setId(Integer.valueOf(String.valueOf(nextid)));
				filepath.setFilepathName(map.get("pathname"));
				filepath.setFileName(map.get("filename"));
				filepath.setFilePath(map.get("path"));
				filepath.setCreateTime(TKDateTimeUtils.getTodayTimeStamp());
				filepath.setOperName("");
				filepathList.add(filepath);
			}
			filepathService.batchInsert(filepathList);
			return true;
		} else {
			return false;
		}
	}

	public String getUpLoadPath() {
		return upLoadPath;
	}

	public void setUpLoadPath(String upLoadPath) {
		this.upLoadPath = upLoadPath;
	}

	@Override
	public String uploadEditor(HttpServletRequest request) {
		Dto param = new BaseDto();
		String pictureUrl = null;
		String upLoadPath = CommUtil.uploadFilePath();
//		String serviceId = request.getParameter("serviceId");
		try {
			pictureUrl = fileloadService.uploadEditor(request, upLoadPath, "");
		} catch (TKCheckedException e) {
		}
//			if(serviceId != null && !"".equals(serviceId)){
//				if(pictureUrl!=null && !"".equals(pictureUrl)){
//					param.put("serviceId", serviceId);
//					param.put("pictureUrl", pictureUrl);
//					
////					SingleBO filePath = BaseDto.toModel(SingleBO.class , param);
////					singleService.updateObject(filePath);
////				
//					return pictureUrl;
//				}
//			}
		return pictureUrl;	
	}
}