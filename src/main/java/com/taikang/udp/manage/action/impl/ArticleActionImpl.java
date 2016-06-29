package com.taikang.udp.manage.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.manage.action.IArticleAction;
import com.taikang.udp.manage.model.ArticleBO;
import com.taikang.udp.manage.model.ArticleClassBO;
import com.taikang.udp.manage.service.IArticleClassService;
import com.taikang.udp.manage.service.IArticleService;

/**
  * ArticleAction
  */
  @Service(IArticleAction.ACTION_ID)
public class ArticleActionImpl extends BaseActionImpl 
  implements IArticleAction {

    /**
      * 注入service
      */
    @Resource(name=IArticleService.SERVICE_ID)
	private IArticleService<ArticleBO> articleService;	
    
    @Resource(name=IArticleClassService.SERVICE_ID)
	private IArticleClassService<ArticleClassBO> articleClassService;
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ArticleAction--addArticle======>");
		
		ArticleBO articleBO = BaseDto.toModel(ArticleBO.class , param);
		articleService.insertObject(articleBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ArticleAction--updateArticle======>");
		
		ArticleBO articleBO = BaseDto.toModel(ArticleBO.class , param);
		articleService.updateObject(articleBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ArticleAction--deleteArticle======>");
		
		ArticleBO articleBO = BaseDto.toModel(ArticleBO.class , param);
		articleService.deleteObject(articleBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ArticleAction--findOneArticle======>");
		
		ArticleBO articleBO = BaseDto.toModel(ArticleBO.class , param);
		return articleService.findOne(articleBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ArticleAction--findAllArticle======>");
				
		return articleService.findAllMap(param);
	} 
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ArticleAction--queryArticleForPage======>");
		
		return articleService.queryForPage(currentPage);
	}

	@Override
	public List<ArticleBO> findByArticleByClass(ArticleBO param)
			throws TKBizException {
		return articleService.findByArticleByClass(param);
	}

	@Override
	public ArticleBO findOne(ArticleBO param) {
		ArticleBO bo = articleService.findOne(param);
		ArticleClassBO acbo = new ArticleClassBO();
		acbo.setId(bo.getArticleclassId());
		acbo = articleClassService.findOne(acbo);
		bo.setArticleClass(acbo);
		return bo;
	}

	@Override
	public void deleteObject(ArticleBO param) {
		articleService.deleteObject(param);
	}

	@Override
	public void insertObject(ArticleBO param) {
		articleService.insertObject(param);
	}

	@Override
	public void updateObject(ArticleBO param) {
		articleService.updateObject(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void decorateArticleList(List<Dto> list) {
		ArticleClassBO acbo = new ArticleClassBO();
		if(list.size() > 0){
			for(Dto dto:list){
				acbo.setId(dto.getAsLong("articleClass_id"));
				ArticleClassBO _bo = articleClassService.findOne(acbo);
				dto.put("articleClass", _bo);
			}
		}
	}	
}
