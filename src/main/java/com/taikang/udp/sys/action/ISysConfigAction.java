package com.taikang.udp.sys.action;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.sys.model.SysConfigBO;


/**
  * ISysConfigAction
  */

public interface ISysConfigAction extends IBaseAction { 

	final String ACTION_ID = "sysConfigAction";
	
	public BaseBO findOne(SysConfigBO param);
	
	public SysConfigBO getSysConfig();
	
}
