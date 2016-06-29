package com.taikang.tkcoww.news.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.tkcoww.news.service.INewsService;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.news.action.INewsAction;
import com.taikang.tkcoww.news.model.NewsBO;

/**
  * NewsAction
  */
  @Service(INewsAction.ACTION_ID)
public class NewsActionImpl extends BaseActionImpl 
  implements INewsAction {

    /**
      * 注入service
      */
    @Resource(name=INewsService.SERVICE_ID)
	private INewsService<NewsBO> newsService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======NewsAction--addNews======>");
		
		NewsBO newsBO = BaseDto.toModel(NewsBO.class , param);
		newsService.insertObject(newsBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======NewsAction--updateNews======>");
		
		NewsBO newsBO = BaseDto.toModel(NewsBO.class , param);
		newsService.updateObject(newsBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======NewsAction--deleteNews======>");
		
		NewsBO newsBO = BaseDto.toModel(NewsBO.class , param);
		newsService.deleteObject(newsBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======NewsAction--findOneNews======>");
		
		NewsBO newsBO = BaseDto.toModel(NewsBO.class , param);
		return newsService.findOne(newsBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======NewsAction--findAllNews======>");
				
		return newsService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======NewsAction--queryNewsForPage======>");
		
		return newsService.queryForPage(currentPage);
	}	
}
