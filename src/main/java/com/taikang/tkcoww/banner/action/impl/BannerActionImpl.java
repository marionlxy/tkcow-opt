package com.taikang.tkcoww.banner.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.tkcoww.banner.model.BannerBO;
import com.taikang.tkcoww.banner.service.IBannerService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;

import java.util.Arrays;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.util.StringTokenizerUtils;
import com.taikang.udp.sys.service.IFileloadService;

import org.springframework.stereotype.Service;

import com.taikang.tkcoww.banner.action.IBannerAction;

/**
  * BannerAction
  */
  @Service(IBannerAction.ACTION_ID)
public class BannerActionImpl extends BaseActionImpl 
  implements IBannerAction {

    /**
      * 注入service
      */
    @Resource(name=IBannerService.SERVICE_ID)
	private IBannerService<BannerBO> bannerService;	
    /**
  	 * 注入service
  	 */
  	@Resource(name = IFileloadService.SERVICE_ID)
  	private IFileloadService fileloadService;
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======BannerAction--addBanner======>");
		
		BannerBO bannerBO = BaseDto.toModel(BannerBO.class , param);
		bannerService.insertObject(bannerBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======BannerAction--updateBanner======>");
		
		BannerBO bannerBO = BaseDto.toModel(BannerBO.class , param);
		bannerService.updateObject(bannerBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======BannerAction--deleteBanner======>");
		
		BannerBO bannerBO = BaseDto.toModel(BannerBO.class , param);
		bannerService.deleteObject(bannerBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======BannerAction--findOneBanner======>");
		
		BannerBO bannerBO = BaseDto.toModel(BannerBO.class , param);
		return bannerService.findOne(bannerBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======BannerAction--findAllBanner======>");
				
		return bannerService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======BannerAction--queryBannerForPage======>");
		
		return bannerService.queryForPage(currentPage);
	}	
	
	@Override
	public String uploadBySpringGrpBanner(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		String pictureUrl = null;
//		String upLoadPath = CommonUtil.uploadFilePath();//  /upload/
		String upLoadPath = null;
		String banImgId = request.getParameter("banImgId");
		try {
			pictureUrl = fileloadService.uploadBySpringGrpFixed(request,upLoadPath,"banner","");
		} catch (TKCheckedException e) {
		}
		if(banImgId != null && !"".equals(banImgId)){
			if(pictureUrl!=null && !"".equals(pictureUrl)){
				
				String [] str = StringTokenizerUtils.split(pictureUrl, ",");
				
				param.put("banImgId", banImgId);
				param.put("banImgUrl", str[0]);
				if(str.length>1){
					param.put("banImgOutside", str[1]);
				}
				
				BannerBO filePath = BaseDto.toModel(BannerBO.class , param);
				bannerService.updateObject(filePath);
				
			
				return pictureUrl;
			}
		}
		
		return null;
	}


	public CurrentPage getBannerList(CurrentPage page) {
		logger.debug("<======BannerAction--getBannerList======>");
		return bannerService.getBannerList(page);
	}
}
