package com.taikang.udp.manage.action;
import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.manage.model.ArticleBO;

/**
  * IArticleAction
  */

public interface IArticleAction extends IBaseAction { 

	final String ACTION_ID = "articleAction";
	
	public List<ArticleBO> findByArticleByClass(ArticleBO param) throws TKBizException;
	public ArticleBO findOne(ArticleBO param);
	
	/**
	 * 删除一条数据
	 * @param param
	 */
	public void deleteObject(ArticleBO param);
	
	public void insertObject(ArticleBO param);
	
	public void updateObject(ArticleBO param);
	
	public void decorateArticleList(List<Dto> param);
	
}
