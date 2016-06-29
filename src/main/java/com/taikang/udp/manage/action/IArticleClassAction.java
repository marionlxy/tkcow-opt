package com.taikang.udp.manage.action;

import java.util.List;
import java.util.Set;

import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.manage.model.ArticleClassBO;


/**
  * IArticleClassAction
  */

public interface IArticleClassAction extends IBaseAction { 

	final String ACTION_ID = "articleClassAction"; 
	
	//
	public List<ArticleClassBO> listRoot();
	
	/**
	 * 删除一条数据
	 * @param param
	 */
	public void deleteObject(ArticleClassBO param);
	
	public ArticleClassBO findOne(ArticleClassBO param);
	
	public Set<Long> genericIds(ArticleClassBO ac);
	
	public void insertObject(ArticleClassBO param);
	
	public void updateObject(ArticleClassBO param);
	
	
	public void fillChilds(ArticleClassBO acs);
	
	
	
	
	
}
