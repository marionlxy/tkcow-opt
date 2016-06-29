package com.taikang.udp.manage.service.impl;
 
import com.taikang.udp.manage.service.IAdvertPositionService;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.manage.model.AdvertPositionBO;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * AdvertPositionServiceImpl
  */
 @Service(IAdvertPositionService.SERVICE_ID)
public class AdvertPositionServiceImpl extends BaseServiceImpl implements IAdvertPositionService<AdvertPositionBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(AdvertPositionBO advertPosition) {
		logger.debug("<======AdvertPositionServiceImpl--insertObject======>");		
		appDao.insert("AdvertPosition.addAdvertPosition",advertPosition);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(AdvertPositionBO advertPosition) {
		logger.debug("<======AdvertPositionServiceImpl--updateObject======>");
		appDao.update("AdvertPosition.updateAdvertPosition",advertPosition);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(AdvertPositionBO advertPosition) {
		logger.debug("<======AdvertPositionServiceImpl--deleteObject======>");
		appDao.delete("AdvertPosition.deleteAdvertPosition",advertPosition);
	}
	
	/**
      * 查询数据
      */
	public AdvertPositionBO findOne(AdvertPositionBO advertPosition) {
		logger.debug("<======AdvertPositionServiceImpl--findOne======>");
		
		AdvertPositionBO advertPositionBackBO=appDao.queryOneObject("AdvertPosition.findOneAdvertPosition", advertPosition);
		return advertPositionBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<AdvertPositionBO> findAll(AdvertPositionBO  advertPosition) {
		logger.debug("<======AdvertPositionServiceImpl--findAll======>");
		return appDao.queryForEntityList("AdvertPosition.findAllAdvertPosition", advertPosition);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======AdvertPositionServiceImpl--queryForPage======>");
		return appDao.queryForPage("AdvertPosition.queryAdvertPositionForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======AdvertPositionServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("AdvertPosition.findAllMapAdvertPosition", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<AdvertPositionBO> advertPositionList){
		logger.debug("<======AdvertPositionServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("AdvertPosition.addAdvertPosition" ,advertPositionList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<AdvertPositionBO> advertPositionList){
		logger.debug("<======AdvertPositionServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("AdvertPosition.updateAdvertPosition" ,advertPositionList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<AdvertPositionBO> advertPositionList){
		logger.debug("<======AdvertPositionServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("AdvertPosition.deleteAdvertPosition" ,advertPositionList);
	}
}
  