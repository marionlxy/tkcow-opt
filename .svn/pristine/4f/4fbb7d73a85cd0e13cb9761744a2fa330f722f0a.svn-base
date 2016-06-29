package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.ISysParamAction;
import com.taikang.udp.sys.model.SysParamBO;
import com.taikang.udp.sys.service.ISysParamService;

/**
  * SysParamAction
  */
  @Service(ISysParamAction.ACTION_ID)
public class SysParamActionImpl extends BaseActionImpl 
  implements ISysParamAction {

    /**
      * 注入service
      */
    @Resource(name=ISysParamService.SERVICE_ID)
	private ISysParamService<SysParamBO> sysParamService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======SysParamAction--addSysParam======>");
		
		SysParamBO sysParamBO = BaseDto.toModel(SysParamBO.class , param);
		sysParamService.insertObject(sysParamBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======SysParamAction--updateSysParam======>");
		
		SysParamBO sysParamBO = BaseDto.toModel(SysParamBO.class , param);
		sysParamService.updateObject(sysParamBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======SysParamAction--deleteSysParam======>");
		
		SysParamBO sysParamBO = BaseDto.toModel(SysParamBO.class , param);
		sysParamService.deleteObject(sysParamBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======SysParamAction--findOneSysParam======>");
		
		SysParamBO sysParamBO = BaseDto.toModel(SysParamBO.class , param);
		return sysParamService.findOne(sysParamBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======SysParamAction--findAllSysParam======>");
				
		return sysParamService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======SysParamAction--querySysParamForPage======>");
		
		return sysParamService.queryForPage(currentPage);
	}	
}
