package com.taikang.udp.manage.action;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.manage.model.AdvertBO;


/**
  * IAdvertAction
  */

public interface IAdvertAction extends IBaseAction { 

	final String ACTION_ID = "advertAction"; 
	
	public AdvertBO findOne(AdvertBO param);
	
	public void insertObject(AdvertBO param);
	
	public void updateObject(AdvertBO param);
	
	public List<AdvertBO> findAll(AdvertBO param);
	
	public void  decorateAdvertList(List<Dto> param);
	
}
