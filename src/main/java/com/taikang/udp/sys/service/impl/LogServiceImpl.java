package com.taikang.udp.sys.service.impl;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.LogBO;
import com.taikang.udp.sys.service.ILogService;
 
  
/**
  * LogServiceImpl
  */
 @Service(ILogService.SERVICE_ID)
 public class LogServiceImpl extends BaseServiceImpl 
 implements ILogService<LogBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(LogBO log) {
		logger.debug("<======LogServiceImpl--insertLog======>");		
		appDao.insert("Log.addLog",log);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(LogBO log) {
		logger.debug("<======LogServiceImpl--updateLog======>");
		appDao.update("Log.updateLog",log);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(LogBO log) {
		logger.debug("<======LogServiceImpl--deleteLog======>");
		appDao.delete("Log.deleteLog",log);
	}
	
	/**
      * 查询数据
      */
	public LogBO findOne(LogBO log) {
		logger.debug("<======LogServiceImpl--findLog======>");
		
		LogBO logBackBO=appDao.queryOneObject("Log.findOneLog", log);
		return logBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<LogBO> findAll(LogBO  log) {
		logger.debug("<======LogServiceImpl--findAllLog======>");
		return appDao.queryForEntityList("Log.findAllLog", log);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======LogServiceImpl--queryLogForPage======>");
		return appDao.queryForPage("Log.queryLogForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======LogServiceImpl--findAllMapLog======>");		
		return appDao.queryForMapList("Log.findAllMapLog", param);
	}
	/**
	 * 按条件分页查询数据
	 * @param currentPage
	 * @return CurrentPage
	 */
	public CurrentPage queryByconForPage(CurrentPage currentPage) {
		logger.debug("<======LogServiceImpl--queryBycomForPage======>");
		return appDao.queryForPage("Log.searchLogByconForPage", currentPage);
	}
 }
  