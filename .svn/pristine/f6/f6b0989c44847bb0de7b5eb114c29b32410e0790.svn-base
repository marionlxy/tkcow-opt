package com.taikang.tkcoww.banner.service.impl;
 
import java.util.List;
import com.taikang.tkcoww.banner.model.BannerBO;
import com.taikang.tkcoww.banner.service.IBannerService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * BannerServiceImpl
  */
 @Service(IBannerService.SERVICE_ID)
 public class BannerServiceImpl extends BaseServiceImpl 
 implements IBannerService<BannerBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(BannerBO banner) {
		logger.debug("<======BannerServiceImpl--insertBanner======>");		
		appDao.insert("Banner.addBanner",banner);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(BannerBO banner) {
		logger.debug("<======BannerServiceImpl--updateBanner======>");
		appDao.update("Banner.updateBanner",banner);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(BannerBO banner) {
		logger.debug("<======BannerServiceImpl--deleteBanner======>");
		appDao.delete("Banner.deleteBanner",banner);
	}
	
	/**
      * 查询数据
      */
	public BannerBO findOne(BannerBO banner) {
		logger.debug("<======BannerServiceImpl--findBanner======>");
		
		BannerBO bannerBackBO=appDao.queryOneObject("Banner.findOneBanner", banner);
		return bannerBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<BannerBO> findAll(BannerBO  banner) {
		logger.debug("<======BannerServiceImpl--findAllBanner======>");
		return appDao.queryForEntityList("Banner.findAllBanner", banner);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======BannerServiceImpl--queryBannerForPage======>");
		return appDao.queryForPage("Banner.queryBannerForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======BannerServiceImpl--findAllMapBanner======>");		
		return appDao.queryForMapList("Banner.findAllMapBanner", param);
	}

	@Override
	public CurrentPage getBannerList(CurrentPage page) {
		logger.debug("<======BannerServiceImpl--queryBannerForPage======>");
		return appDao.queryForPage("Banner.getBannerList", page);
	}
 }
  