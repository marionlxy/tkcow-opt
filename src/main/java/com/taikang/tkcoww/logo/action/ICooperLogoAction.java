package com.taikang.tkcoww.logo.action;

import java.util.Arrays;

import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;


/**
  * ICooperLogoAction
  */

public interface ICooperLogoAction extends IBaseAction { 

	final String ACTION_ID = "cooperLogoAction"; 	
	public CurrentPage getLogoModelPage(CurrentPage page);
	/**
	 * @author itw_lixy02
	 * getTypeCooperLogoList
	 * ICooperLogoAction
	 * @param page
	 * @return 模糊查询当loogo类型信息
	 */
	public CurrentPage getTypeCooperLogoList(CurrentPage page);
}
