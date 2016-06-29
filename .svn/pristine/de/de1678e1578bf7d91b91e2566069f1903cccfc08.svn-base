package com.taikang.tkcoww.moduleDes.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.taikang.tkcoww.moduleDes.action.IModuleDesAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.tkcoww.moduleDes.model.ModuleDesBO;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;

import java.util.Arrays;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.util.StringTokenizerUtils;
import com.taikang.udp.sys.model.LogBO;
import com.taikang.udp.sys.service.IFileloadService;
import com.taikang.udp.sys.util.CommonUtil;

import org.springframework.stereotype.Service;

import com.taikang.tkcoww.moduleDes.service.IModuleDesService;

/**
  * ModuleDesAction
  */
  @Service(IModuleDesAction.ACTION_ID)
public class ModuleDesActionImpl extends BaseActionImpl 
  implements IModuleDesAction {

    /**
      * 注入service
      */
    @Resource(name=IModuleDesService.SERVICE_ID)
	private IModuleDesService<ModuleDesBO> moduleDesService;	
    /**
	 * 注入service
	 */
	@Resource(name = IFileloadService.SERVICE_ID)
	private IFileloadService fileloadService;
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ModuleDesAction--addModuleDes======>");
		
		ModuleDesBO moduleDesBO = BaseDto.toModel(ModuleDesBO.class , param);
		moduleDesService.insertObject(moduleDesBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ModuleDesAction--updateModuleDes======>");
		
		ModuleDesBO moduleDesBO = BaseDto.toModel(ModuleDesBO.class , param);
		moduleDesService.updateObject(moduleDesBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ModuleDesAction--deleteModuleDes======>");
		
		ModuleDesBO moduleDesBO = BaseDto.toModel(ModuleDesBO.class , param);
		moduleDesService.deleteObject(moduleDesBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ModuleDesAction--findOneModuleDes======>");
		
		ModuleDesBO moduleDesBO = BaseDto.toModel(ModuleDesBO.class , param);
		return moduleDesService.findOne(moduleDesBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ModuleDesAction--findAllModuleDes======>");
				
		return moduleDesService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ModuleDesAction--queryModuleDesForPage======>");
		
		return moduleDesService.queryForPage(currentPage);
	}	
	
	@Override
	public String uploadBySpringGrpModuleDes(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		String pictureUrl = null;
//		String upLoadPath = CommonUtil.uploadFilePath();//  /upload/
		String upLoadPath = null;
		String modId = request.getParameter("modId");
		try {
			pictureUrl = fileloadService.uploadBySpringGrpFixed(request,upLoadPath,"moduleDes","");
		} catch (TKCheckedException e) {
		}
		if(modId != null && !"".equals(modId)){
			if(pictureUrl!=null && !"".equals(pictureUrl)){
				
				String [] str = StringTokenizerUtils.split(pictureUrl, ",");
				
				param.put("modId", modId);
				param.put("modBanner", str[0]);
				if(str.length>1){
					param.put("modImg", str[1]);
				}
				
				ModuleDesBO filePath = BaseDto.toModel(ModuleDesBO.class , param);
				moduleDesService.updateObject(filePath);
				
				//得到 最新的 log_id
//				LogBO log = new LogBO();
////				log.setClientIp("0:0:0:0:0:0:0:1");
//				
////				log.setLogType(3);
//				log = logService.findNewLog(log);
////				log = logService.findOne(log);
//				
//				//写入日志
//				log.set("message", "上传成功");
//				log.set("oper_name", "上传");
////				log.set("createTime", log.getCreateTime());
//				
//				logService.updateObject(log);
			
				return pictureUrl;
			}
		}
		return null;
	}
}
