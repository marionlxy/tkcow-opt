package com.taikang.udp.accessory.service.impl;
 
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.accessory.service.IAccessoryService;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * AccessoryServiceImpl
  */
 @Service(IAccessoryService.SERVICE_ID)
public class AccessoryServiceImpl extends BaseServiceImpl implements IAccessoryService<AccessoryBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(AccessoryBO accessory) {
		logger.debug("<======AccessoryServiceImpl--insertObject======>");		
		appDao.insert("Accessory.addAccessory",accessory);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(AccessoryBO accessory) {
		logger.debug("<======AccessoryServiceImpl--updateObject======>");
		appDao.update("Accessory.updateAccessory",accessory);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(AccessoryBO accessory) {
		logger.debug("<======AccessoryServiceImpl--deleteObject======>");
		appDao.delete("Accessory.deleteAccessory",accessory);
	}
	
	/**
      * 查询数据
      */
	public AccessoryBO findOne(AccessoryBO accessory) {
		logger.debug("<======AccessoryServiceImpl--findOne======>");
		
		AccessoryBO accessoryBackBO=appDao.queryOneObject("Accessory.findOneAccessory", accessory);
		return accessoryBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<AccessoryBO> findAll(AccessoryBO  accessory) {
		logger.debug("<======AccessoryServiceImpl--findAll======>");
		return appDao.queryForEntityList("Accessory.findAllAccessory", accessory);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======AccessoryServiceImpl--queryForPage======>");
		return appDao.queryForPage("Accessory.queryAccessoryForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======AccessoryServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Accessory.findAllMapAccessory", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<AccessoryBO> accessoryList){
		logger.debug("<======AccessoryServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Accessory.addAccessory" ,accessoryList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<AccessoryBO> accessoryList){
		logger.debug("<======AccessoryServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("Accessory.updateAccessory" ,accessoryList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<AccessoryBO> accessoryList){
		logger.debug("<======AccessoryServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("Accessory.deleteAccessory" ,accessoryList);
	}
}
  