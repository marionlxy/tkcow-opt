package com.taikang.udp.framwork.dao;

import java.util.List;

import org.junit.Test;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.testsupport.AbstractTest;
import com.taikang.udp.framework.core.persistence.dao.IBaseDao;
import com.taikang.udp.sys.model.UserBO;

public class BaseDaoImplTest extends AbstractTest{
	
	@Test
	public void testQuery(){
		Dto param = new BaseDto();
		param.put("user_status", "3");
		List<UserBO> test = appDao.queryForEntityListByDto("UserTest.findAllUser", param, new UserBO());
		System.out.println(test.size());
	}
}
