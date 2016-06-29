package com.taikang.tkcoww.module.action;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;

import java.util.Arrays;


/**
  * IModuleAction
  */

public interface IModuleAction extends IBaseAction { 

	final String ACTION_ID = "moduleAction";

	/**
	 * @author itw_lixy02
	 * queryNavMenuForPage
	 * IModuleAction
	 * @param page
	 * @return
	 */
	CurrentPage queryNavMenuForPage(CurrentPage page);

	/**
	 * @author itw_lixy02
	 * findSubOneModule
	 * IModuleAction
	 * @param param
	 * @return
	 */
	Dto findSubOneModule(Dto param);

	/**
	 * @author itw_lixy02
	 * querySubMenu
	 * IModuleAction
	 * @param page
	 * @return
	 */
	CurrentPage querySubMenu(CurrentPage page);

	/**
	 * @author itw_lixy02
	 * updateSubObject
	 * IModuleAction
	 * @param dto
	 */
	void updateSubObject(Dto dto);

	/**
	 * @author itw_lixy02
	 * queryModulelist
	 * IModuleAction
	 * @param page
	 * @return
	 */
	CurrentPage queryModulelist(CurrentPage page); 
	
	public Dto findNewOne(Dto param) ;
}
