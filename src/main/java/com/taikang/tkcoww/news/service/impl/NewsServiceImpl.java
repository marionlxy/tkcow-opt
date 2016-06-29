package com.taikang.tkcoww.news.service.impl;
 
import com.taikang.tkcoww.news.service.INewsService;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.news.model.NewsBO;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * NewsServiceImpl
  */
 @Service(INewsService.SERVICE_ID)
 public class NewsServiceImpl extends BaseServiceImpl 
 implements INewsService<NewsBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(NewsBO news) {
		logger.debug("<======NewsServiceImpl--insertNews======>");		
		appDao.insert("News.addNews",news);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(NewsBO news) {
		logger.debug("<======NewsServiceImpl--updateNews======>");
		appDao.update("News.updateNews",news);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(NewsBO news) {
		logger.debug("<======NewsServiceImpl--deleteNews======>");
		appDao.delete("News.deleteNews",news);
	}
	
	/**
      * 查询数据
      */
	public NewsBO findOne(NewsBO news) {
		logger.debug("<======NewsServiceImpl--findNews======>");
		
		NewsBO newsBackBO=appDao.queryOneObject("News.findOneNews", news);
		return newsBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<NewsBO> findAll(NewsBO  news) {
		logger.debug("<======NewsServiceImpl--findAllNews======>");
		return appDao.queryForEntityList("News.findAllNews", news);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======NewsServiceImpl--queryNewsForPage======>");
		return appDao.queryForPage("News.queryNewsForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======NewsServiceImpl--findAllMapNews======>");		
		return appDao.queryForMapList("News.findAllMapNews", param);
	}
 }
  