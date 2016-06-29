package com.taikang.udp.manage.service.impl;
 
import com.taikang.udp.manage.model.ArticleClassBO;

import java.util.List;

import com.taikang.udp.manage.service.IArticleClassService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ArticleClassServiceImpl
  */
 @Service(IArticleClassService.SERVICE_ID)
public class ArticleClassServiceImpl extends BaseServiceImpl implements IArticleClassService<ArticleClassBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ArticleClassBO articleClass) {
		logger.debug("<======ArticleClassServiceImpl--insertObject======>");		
		appDao.insert("ArticleClass.addArticleClass",articleClass);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ArticleClassBO articleClass) {
		logger.debug("<======ArticleClassServiceImpl--updateObject======>");
		appDao.update("ArticleClass.updateArticleClass",articleClass);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ArticleClassBO articleClass) {
		logger.debug("<======ArticleClassServiceImpl--deleteObject======>");
		appDao.delete("ArticleClass.deleteArticleClass",articleClass);
	}
	
	/**
      * 查询数据
      */
	public ArticleClassBO findOne(ArticleClassBO articleClass) {
		logger.debug("<======ArticleClassServiceImpl--findOne======>");
		
		ArticleClassBO articleClassBackBO=appDao.queryOneObject("ArticleClass.findOneArticleClass", articleClass);
		return articleClassBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ArticleClassBO> findAll(ArticleClassBO  articleClass) {
		logger.debug("<======ArticleClassServiceImpl--findAll======>");
		return appDao.queryForEntityList("ArticleClass.findAllArticleClass", articleClass);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ArticleClassServiceImpl--queryForPage======>");
		return appDao.queryForPage("ArticleClass.queryArticleClassForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ArticleClassServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("ArticleClass.findAllMapArticleClass", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<ArticleClassBO> articleClassList){
		logger.debug("<======ArticleClassServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("ArticleClass.addArticleClass" ,articleClassList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<ArticleClassBO> articleClassList){
		logger.debug("<======ArticleClassServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("ArticleClass.updateArticleClass" ,articleClassList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<ArticleClassBO> articleClassList){
		logger.debug("<======ArticleClassServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("ArticleClass.deleteArticleClass" ,articleClassList);
	}

	@Override
	public List<ArticleClassBO> listRoot() {
		return appDao.queryForEntityList("ArticleClass.listRoot",new ArticleClassBO());
	}

	@Override
	public List<ArticleClassBO> listChilds(Long parentID) {
		ArticleClassBO bo = new ArticleClassBO();
		bo.setParentId(parentID);
		return appDao.queryForEntityList("ArticleClass.listChilds",bo);
	}
	
	
}
  