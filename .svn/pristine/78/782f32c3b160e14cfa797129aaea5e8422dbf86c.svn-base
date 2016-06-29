package com.taikang.udp.file.service.impl;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.taikang.udp.file.model.FilepathBO;
import com.taikang.udp.file.service.IFilepathService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * FilepathServiceImpl
  */
 @Service(IFilepathService.SERVICE_ID)
public class FilepathServiceImpl extends BaseServiceImpl implements IFilepathService<FilepathBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(FilepathBO filepath) {
		logger.debug("<======FilepathServiceImpl--insertObject======>");		
		appDao.insert("Filepath.addFilepath",filepath);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(FilepathBO filepath) {
		logger.debug("<======FilepathServiceImpl--updateObject======>");
		appDao.update("Filepath.updateFilepath",filepath);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(FilepathBO filepath) {
		logger.debug("<======FilepathServiceImpl--deleteObject======>");
		appDao.delete("Filepath.deleteFilepath",filepath);
	}
	
	/**
      * 查询数据
      */
	public FilepathBO findOne(FilepathBO filepath) {
		logger.debug("<======FilepathServiceImpl--findOne======>");
		
		FilepathBO filepathBackBO=appDao.queryOneObject("Filepath.findOneFilepath", filepath);
		return filepathBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<FilepathBO> findAll(FilepathBO  filepath) {
		logger.debug("<======FilepathServiceImpl--findAll======>");
		return appDao.queryForEntityList("Filepath.findAllFilepath", filepath);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======FilepathServiceImpl--queryForPage======>");
		return appDao.queryForPage("Filepath.queryFilepathForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======FilepathServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Filepath.findAllMapFilepath", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<FilepathBO> filepathList){
		logger.debug("<======FilepathServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Filepath.addFilepath" ,filepathList);
	}

}
  