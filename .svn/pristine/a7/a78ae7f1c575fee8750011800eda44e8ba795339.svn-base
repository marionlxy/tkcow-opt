package com.taikang.udp.view.action;

import java.util.List;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.view.model.NavigationBO;


/**
  * INavigationAction
  */

public interface INavigationAction extends IBaseAction { 

	final String ACTION_ID = "navigationAction"; 
	
	public List<NavigationBO> findAll(NavigationBO param) throws TKBizException;
	
	public void deleteObject(NavigationBO param);
	
	public void insertObject(NavigationBO param);
	
	public void updateObject(NavigationBO param);
	
	/**
	 * 查找一条记录，通常都是按主键来查询一条信息
	 * @param param
	 * @return Dto
	 */
	public NavigationBO findOne(NavigationBO param);
	
	
	
}
