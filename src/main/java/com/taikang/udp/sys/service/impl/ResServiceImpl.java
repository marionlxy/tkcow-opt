package com.taikang.udp.sys.service.impl;
 
import com.taikang.udp.sys.service.IResService;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.sys.model.ResBO;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ResServiceImpl
  */
 @Service(IResService.SERVICE_ID)
public class ResServiceImpl extends BaseServiceImpl implements IResService<ResBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ResBO res) {
		logger.debug("<======ResServiceImpl--insertObject======>");		
		appDao.insert("Res.addRes",res);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ResBO res) {
		logger.debug("<======ResServiceImpl--updateObject======>");
		appDao.update("Res.updateRes",res);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ResBO res) {
		logger.debug("<======ResServiceImpl--deleteObject======>");
		appDao.delete("Res.deleteRes",res);
	}
	
	/**
      * 查询数据
      */
	public ResBO findOne(ResBO res) {
		logger.debug("<======ResServiceImpl--findOne======>");
		
		ResBO resBackBO=appDao.queryOneObject("Res.findOneRes", res);
		return resBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ResBO> findAll(ResBO  res) {
		logger.debug("<======ResServiceImpl--findAll======>");
		return appDao.queryForEntityList("Res.findAllRes", res);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ResServiceImpl--queryForPage======>");
		return appDao.queryForPage("Res.queryResForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ResServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Res.findAllMapRes", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<ResBO> resList){
		logger.debug("<======ResServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Res.addRes" ,resList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<ResBO> resList){
		logger.debug("<======ResServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("Res.updateRes" ,resList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<ResBO> resList){
		logger.debug("<======ResServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("Res.deleteRes" ,resList);
	}
}
  