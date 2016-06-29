package com.taikang.udp.manage.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;

import javax.annotation.Resource;

import com.taikang.udp.manage.model.ArticleClassBO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.manage.service.IArticleClassService;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;

import org.springframework.stereotype.Service;

import com.taikang.udp.manage.action.IArticleClassAction;

/**
  * ArticleClassAction
  */
  @Service(IArticleClassAction.ACTION_ID)
public class ArticleClassActionImpl extends BaseActionImpl 
  implements IArticleClassAction {

    /**
      * 注入service
      */
    @Resource(name=IArticleClassService.SERVICE_ID)
	private IArticleClassService<ArticleClassBO> articleClassService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ArticleClassAction--addArticleClass======>");
		
		ArticleClassBO articleClassBO = BaseDto.toModel(ArticleClassBO.class , param);
		articleClassService.insertObject(articleClassBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ArticleClassAction--updateArticleClass======>");
		
		ArticleClassBO articleClassBO = BaseDto.toModel(ArticleClassBO.class , param);
		articleClassService.updateObject(articleClassBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ArticleClassAction--deleteArticleClass======>");
		
		ArticleClassBO articleClassBO = BaseDto.toModel(ArticleClassBO.class , param);
		articleClassService.deleteObject(articleClassBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ArticleClassAction--findOneArticleClass======>");
		
		ArticleClassBO articleClassBO = BaseDto.toModel(ArticleClassBO.class , param);
		return articleClassService.findOne(articleClassBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ArticleClassAction--findAllArticleClass======>");
		return articleClassService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ArticleClassAction--queryArticleClassForPage======>");
		return articleClassService.queryForPage(currentPage);
	}

	@Override
	public List<ArticleClassBO> listRoot() {
		List<ArticleClassBO> childList = articleClassService.listRoot();
		for (ArticleClassBO articleClass : childList) {
			fillChilds(articleClass);
		}
		return childList;
	}
	
	@Override
	public void fillChilds(ArticleClassBO acs){
        List<ArticleClassBO> childList= articleClassService.listChilds(acs.getId());
        if(childList.size()>0){
            for(ArticleClassBO _acs:childList){
                fillChilds(_acs);
            }
        }
        acs.setChilds(childList);
    }

	@Override
	public void deleteObject(ArticleClassBO param) {
		articleClassService.deleteObject(param);
	}

	@Override
	public ArticleClassBO findOne(ArticleClassBO param) {
		return articleClassService.findOne(param);
	}

	@Override
	public Set<Long> genericIds(ArticleClassBO ac) {
		Set<Long> ids = new HashSet<Long>();
        ids.add(ac.getId());
        for (ArticleClassBO child : ac.getChilds()) {
            Set<Long> cids = genericIds(child);
            for (Long cid : cids) {
                ids.add(cid);
            }
            ids.add(child.getId());
        }
        return ids;
	}

	@Override
	public void insertObject(ArticleClassBO param) {
		articleClassService.insertObject(param);
	}

	@Override
	public void updateObject(ArticleClassBO param) {
		articleClassService.updateObject(param);
	}
}
