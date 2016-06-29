package com.taikang.udp.sys.action;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.sys.util.vo.MenuTree;


/**
  * IMenuAction
  */

public interface IMenuAction extends IBaseAction { 

	final String ACTION_ID = "menuAction";
	
	public List<Dto> findAllMenuTreeMap(Dto param);
	
	public List<Dto> findAllMenuTreeLeafMap(Dto param);
	
	/**
	 * 批量处理按钮信息<br/>
	 * @param param
	 * @return
	 */
	public void bachDealButtons(Dto param) ;
	
	/**
	 * 获取指定用户页面按钮信息<br/>
	 * @param param
	 * @return
	 */
	public List<Dto> getbtnAccess(Dto param);
	
    /**
	 * 获取指定菜单的菜单树信息<br/>
	 * @param param
	 * @return
	 */
	public List<MenuTree> getMenuTree(String parentId, String state,
	        String currId,String parentIcon, String childIcon);
}
