package com.taikang.tkcoww.banner.action;

import javax.servlet.http.HttpServletRequest;

import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;


/**
  * IBannerAction
  */

public interface IBannerAction extends IBaseAction { 

	final String ACTION_ID = "bannerAction";

	String uploadBySpringGrpBanner(HttpServletRequest request); 	
	public CurrentPage getBannerList(CurrentPage page);
}
