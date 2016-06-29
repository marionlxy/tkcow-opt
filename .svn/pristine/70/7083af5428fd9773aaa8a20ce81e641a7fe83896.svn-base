package com.taikang.udp.manage.service;
import com.taikang.udp.framework.core.service.IBaseService;
import com.taikang.udp.manage.model.ArticleClassBO;

import java.util.List;

/**
  * IArticleClassService
  */
 
public interface IArticleClassService<T> extends IBaseService<T>{
 
	final String SERVICE_ID = "articleClassService";  
 	
 	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<T> articleClassList);
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<T> articleClassList);
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<T> articleClassList);
	
	public List<ArticleClassBO> listRoot() ;
	
	//根据父ID获取所有子集合
    public List<ArticleClassBO> listChilds(Long parentID);
	
	
}
 
 
 