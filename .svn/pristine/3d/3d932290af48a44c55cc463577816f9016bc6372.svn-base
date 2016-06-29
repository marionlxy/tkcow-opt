package com.taikang.udp.manage.service;

import java.util.List;

import com.taikang.udp.manage.model.ArticleBO;
import com.taikang.udp.framework.core.service.IBaseService;

/**
  * IArticleService
  */
 
public interface IArticleService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "articleService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> articleList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> articleList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> articleList);
	
	public List<ArticleBO> findByArticleByClass(ArticleBO param);
	
}
 
 
 