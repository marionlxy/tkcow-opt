package com.taikang.udp.sys.util.enums;

/**
 * <strong>用户公共状态定义</strong><br>
 * <strong>Create on : 2013-07-10<br></strong>
 * <p><strong>Copyright (C) GIT Co.,Ltd.<br></strong></p>
 * @author git<br>
 */

import com.taikang.udp.framework.common.enums.BaseEnum;

public enum UserStatus implements BaseEnum<String>
{
	Normal("正常","1"),Invalid("已失效","2"),Locking("已锁定","3"),Deleting("已删除","4");
	
	private String desc;
	private String code;
	
	private UserStatus(String desc,String code)
	{
		this.desc = desc;
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}

	public String getDesc()
	{
		return this.desc;
	}
	
}
