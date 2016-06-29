package com.taikang.udp.manage.service.impl;
 
import com.taikang.udp.manage.model.ArticleBO;
import java.util.List;
import com.taikang.udp.manage.service.IArticleService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * ArticleServiceImpl
  */
 @Service(IArticleService.SERVICE_ID)
public class ArticleServiceImpl extends BaseServiceImpl implements IArticleService<ArticleBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ArticleBO article) {
		logger.debug("<======ArticleServiceImpl--insertObject======>");		
		appDao.insert("Article.addArticle",article);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ArticleBO article) {
		logger.debug("<======ArticleServiceImpl--updateObject======>");
		appDao.update("Article.updateArticle",article);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ArticleBO article) {
		logger.debug("<======ArticleServiceImpl--deleteObject======>");
		appDao.delete("Article.deleteArticle",article);
	}
	
	/**
      * 查询数据
      */
	public ArticleBO findOne(ArticleBO article) {
		logger.debug("<======ArticleServiceImpl--findOne======>");
		
		ArticleBO articleBackBO=appDao.queryOneObject("Article.findOneArticle", article);
		return articleBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ArticleBO> findAll(ArticleBO  article) {
		logger.debug("<======ArticleServiceImpl--findAll======>");
		return appDao.queryForEntityList("Article.findAllArticle", article);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ArticleServiceImpl--queryForPage======>");
		return appDao.queryForPage("Article.queryArticleForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ArticleServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Article.findAllMapArticle", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<ArticleBO> articleList){
		logger.debug("<======ArticleServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Article.addArticle" ,articleList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<ArticleBO> articleList){
		logger.debug("<======ArticleServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("Article.updateArticle" ,articleList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<ArticleBO> articleList){
		logger.debug("<======ArticleServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("Article.deleteArticle" ,articleList);
	}

	@Override
	public List<ArticleBO> findByArticleByClass(ArticleBO param) {
		
		logger.debug("<======ArticleServiceImpl--findByArticleByClass======>");
		return appDao.queryForEntityList("Article.findByArticleByClass", param);
		
	}
}
  