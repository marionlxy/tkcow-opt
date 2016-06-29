package com.taikang.udp.sys.action.impl;


import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;

import javax.annotation.Resource;

import com.taikang.udp.sys.model.SysConfigBO;
import com.taikang.udp.sys.action.ISysConfigAction;

import java.util.List;

import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;

import org.springframework.stereotype.Service;

import com.taikang.udp.sys.service.ISysConfigService;

/**
  * SysConfigAction
  */
  @Service(ISysConfigAction.ACTION_ID)
public class SysConfigActionImpl extends BaseActionImpl 
  implements ISysConfigAction {

    public ISysConfigService<SysConfigBO> getSysConfigService() {
		return sysConfigService;
	}

	public void setSysConfigService(ISysConfigService<SysConfigBO> sysConfigService) {
		this.sysConfigService = sysConfigService;
	}

	/**
      * 注入service
      */
    @Resource(name=ISysConfigService.SERVICE_ID)
	private ISysConfigService<SysConfigBO> sysConfigService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======SysConfigAction--addSysConfig======>");
		
		SysConfigBO sysConfigBO = BaseDto.toModel(SysConfigBO.class , param);
		sysConfigService.insertObject(sysConfigBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======SysConfigAction--updateSysConfig======>");
		
		SysConfigBO sysConfigBO = BaseDto.toModel(SysConfigBO.class , param);
		sysConfigService.updateObject(sysConfigBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======SysConfigAction--deleteSysConfig======>");
		
		SysConfigBO sysConfigBO = BaseDto.toModel(SysConfigBO.class , param);
		sysConfigService.deleteObject(sysConfigBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======SysConfigAction--findOneSysConfig======>");
		
		SysConfigBO sysConfigBO = BaseDto.toModel(SysConfigBO.class , param);
		return sysConfigService.findOne(sysConfigBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======SysConfigAction--findAllSysConfig======>");
				
		return sysConfigService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======SysConfigAction--querySysConfigForPage======>");
		
		return sysConfigService.queryForPage(currentPage);
	}

	@Override
	public BaseBO findOne(SysConfigBO param) {
		logger.debug("<======SysConfigAction--findAllSysConfig======>");
		
		return sysConfigService.findOne(param);
		
	}

	@Override
	public SysConfigBO getSysConfig() {
		SysConfigBO sc = new SysConfigBO();
		sc.setId(1l);
		sc = sysConfigService.findOne(sc);
		
		if(sc != null ){
           
           if (sc.getUploadfilepath() == null) {
        	   sc.setUploadfilepath(Globals.UPLOAD_FILE_PATH);
           }
           if (sc.getSyslanguage() == null) {
        	   sc.setSyslanguage(Globals.DEFAULT_SYSTEM_LANGUAGE);
           }
           if (sc.getWebsitename() == null || sc.getWebsitename().equals("")) {
               sc.setWebsitename(Globals.DEFAULT_WBESITE_NAME);
           }
           if (sc.getClosereason() == null || sc.getClosereason().equals("")) {
               sc.setClosereason(Globals.DEFAULT_CLOSE_REASON);
           }
           if (sc.getTitle() == null || sc.getTitle().equals("")) {
               sc.setTitle(Globals.DEFAULT_SYSTEM_TITLE);
           }
           if (sc.getImagesavetype() == null
                   || sc.getImagesavetype().equals("")) {
               sc.setImagesavetype(Globals.DEFAULT_IMAGESAVETYPE);
           }
           if (sc.getImagefilesize() == 0) {
               sc.setImagefilesize(Globals.DEFAULT_IMAGE_SIZE);
           }
           if (sc.getSmallwidth() == 0) {
               sc.setSmallwidth(Globals.DEFAULT_IMAGE_SMALL_WIDTH);
           }
           if (sc.getSmallheight() == 0) {
               sc.setSmallheight(Globals.DEFAULT_IMAGE_SMALL_HEIGH);
           }
           if (sc.getMiddlewidth() == 0) {
               sc.setMiddlewidth(Globals.DEFAULT_IMAGE_MIDDLE_WIDTH);
           }
           if (sc.getMiddleheight() == 0) {
               sc.setMiddleheight(Globals.DEFAULT_IMAGE_MIDDLE_HEIGH);
           }
           if (sc.getBigheight() == 0) {
               sc.setBigheight(Globals.DEFAULT_IMAGE_BIG_HEIGH);
           }
           if (sc.getBigwidth() == 0) {
               sc.setBigwidth(Globals.DEFAULT_IMAGE_BIG_WIDTH);
           }
           if (sc.getImagesuffix() == null || sc.getImagesuffix().equals("")) {
               sc.setImagesuffix(Globals.DEFAULT_IMAGE_SUFFIX);
           }
           if (sc.getMemberIcon() == null) {
               AccessoryBO memberIcon = new AccessoryBO();
               memberIcon.setPath("resources/style/common/images");
               memberIcon.setName("member.jpg");
               sc.setMemberIcon(memberIcon);
           }
           if (sc.getSecuritycodetype() == null
                   || sc.getSecuritycodetype().equals("")) {
               sc.setSecuritycodetype(Globals.SECURITY_CODE_TYPE);
           }
           if (sc.getWebsitecss() == null || sc.getWebsitecss().equals("")) {
               sc.setWebsitecss(Globals.DEFAULT_THEME);
           }
           
        }else{
            
            sc = new SysConfigBO();
            sc.setUploadfilepath(Globals.UPLOAD_FILE_PATH);
            sc.setWebsitename(Globals.DEFAULT_WBESITE_NAME);
            sc.setSyslanguage(Globals.DEFAULT_SYSTEM_LANGUAGE);
            sc.setTitle(Globals.DEFAULT_SYSTEM_TITLE);
            sc.setSecuritycodetype(Globals.SECURITY_CODE_TYPE);
            sc.setEmailenable(Globals.EAMIL_ENABLE);
            sc.setClosereason(Globals.DEFAULT_CLOSE_REASON);
            sc.setImagesavetype(Globals.DEFAULT_IMAGESAVETYPE);
            sc.setImagefilesize(Globals.DEFAULT_IMAGE_SIZE);
            sc.setSmallwidth(Globals.DEFAULT_IMAGE_SMALL_WIDTH);
            sc.setSmallheight(Globals.DEFAULT_IMAGE_SMALL_HEIGH);
            sc.setMiddleheight(Globals.DEFAULT_IMAGE_MIDDLE_HEIGH);
            sc.setMiddlewidth(Globals.DEFAULT_IMAGE_MIDDLE_WIDTH);
            sc.setBigheight(Globals.DEFAULT_IMAGE_BIG_HEIGH);
            sc.setBigwidth(Globals.DEFAULT_IMAGE_BIG_WIDTH);
            sc.setImagesuffix(Globals.DEFAULT_IMAGE_SUFFIX);
            sc.setWebsitecss(Globals.DEFAULT_THEME);
            AccessoryBO memberIcon = new AccessoryBO();
            memberIcon.setPath("resources/style/common/images");
            memberIcon.setName("member.jpg");
            sc.setMemberIcon(memberIcon);
           
        }
		
		return sc;
	}	
}
