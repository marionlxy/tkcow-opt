package com.taikang.udp.sys.service.impl;
 
import com.taikang.udp.sys.model.SysConfigBO;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.sys.service.ISysConfigService;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * SysConfigServiceImpl
  */
 @Service(ISysConfigService.SERVICE_ID)
public class SysConfigServiceImpl extends BaseServiceImpl implements ISysConfigService<SysConfigBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(SysConfigBO sysConfig) {
		logger.debug("<======SysConfigServiceImpl--insertObject======>");		
		appDao.insert("SysConfig.addSysConfig",sysConfig);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(SysConfigBO sysConfig) {
		logger.debug("<======SysConfigServiceImpl--updateObject======>");
		appDao.update("SysConfig.updateSysConfig",sysConfig);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(SysConfigBO sysConfig) {
		logger.debug("<======SysConfigServiceImpl--deleteObject======>");
		appDao.delete("SysConfig.deleteSysConfig",sysConfig);
	}
	
	/**
      * 查询数据
      */
	public SysConfigBO findOne(SysConfigBO sysConfig) {
		logger.debug("<======SysConfigServiceImpl--findOne======>");
		
		SysConfigBO sysConfigBackBO=appDao.queryOneObject("SysConfig.findOneSysConfig", sysConfig);
		return sysConfigBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<SysConfigBO> findAll(SysConfigBO  sysConfig) {
		logger.debug("<======SysConfigServiceImpl--findAll======>");
		return appDao.queryForEntityList("SysConfig.findAllSysConfig", sysConfig);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======SysConfigServiceImpl--queryForPage======>");
		return appDao.queryForPage("SysConfig.querySysConfigForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======SysConfigServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("SysConfig.findAllMapSysConfig", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<SysConfigBO> sysConfigList){
		logger.debug("<======SysConfigServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("SysConfig.addSysConfig" ,sysConfigList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<SysConfigBO> sysConfigList){
		logger.debug("<======SysConfigServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("SysConfig.updateSysConfig" ,sysConfigList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<SysConfigBO> sysConfigList){
		logger.debug("<======SysConfigServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("SysConfig.deleteSysConfig" ,sysConfigList);
	}
}
  