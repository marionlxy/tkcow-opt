package com.taikang.udp.manage.service.impl;
 
import com.taikang.udp.manage.model.AdvertBO;
import com.taikang.udp.manage.service.IAdvertService;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * AdvertServiceImpl
  */
 @Service(IAdvertService.SERVICE_ID)
public class AdvertServiceImpl extends BaseServiceImpl implements IAdvertService<AdvertBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(AdvertBO advert) {
		logger.debug("<======AdvertServiceImpl--insertObject======>");		
		appDao.insert("Advert.addAdvert",advert);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(AdvertBO advert) {
		logger.debug("<======AdvertServiceImpl--updateObject======>");
		appDao.update("Advert.updateAdvert",advert);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(AdvertBO advert) {
		logger.debug("<======AdvertServiceImpl--deleteObject======>");
		appDao.delete("Advert.deleteAdvert",advert);
	}
	
	/**
      * 查询数据
      */
	public AdvertBO findOne(AdvertBO advert) {
		logger.debug("<======AdvertServiceImpl--findOne======>");
		
		AdvertBO advertBackBO=appDao.queryOneObject("Advert.findOneAdvert", advert);
		return advertBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<AdvertBO> findAll(AdvertBO  advert) {
		logger.debug("<======AdvertServiceImpl--findAll======>");
		return appDao.queryForEntityList("Advert.findAllAdvert", advert);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======AdvertServiceImpl--queryForPage======>");
		return appDao.queryForPage("Advert.queryAdvertForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======AdvertServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Advert.findAllMapAdvert", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<AdvertBO> advertList){
		logger.debug("<======AdvertServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Advert.addAdvert" ,advertList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<AdvertBO> advertList){
		logger.debug("<======AdvertServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("Advert.updateAdvert" ,advertList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<AdvertBO> advertList){
		logger.debug("<======AdvertServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("Advert.deleteAdvert" ,advertList);
	}
}
  