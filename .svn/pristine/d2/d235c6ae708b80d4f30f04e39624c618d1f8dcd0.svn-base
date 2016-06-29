package com.taikang.udp.sys.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IComRelaAction;
import com.taikang.udp.sys.model.ComRelaBO;
import com.taikang.udp.sys.service.IComRelaService;

/**
  * ComRelaAction
  */
  @Service(IComRelaAction.ACTION_ID)
public class ComRelaActionImpl extends BaseActionImpl 
  implements IComRelaAction {

    /**
      * 注入service
      */
    @Resource(name=IComRelaService.SERVICE_ID)
	private IComRelaService<ComRelaBO> comRelaService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======ComRelaAction--addComRela======>");
		
		ComRelaBO comRelaBO = BaseDto.toModel(ComRelaBO.class , param);
		comRelaService.insertObject(comRelaBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======ComRelaAction--updateComRela======>");
		
		ComRelaBO comRelaBO = BaseDto.toModel(ComRelaBO.class , param);
		comRelaService.updateObject(comRelaBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======ComRelaAction--deleteComRela======>");
		
		ComRelaBO comRelaBO = BaseDto.toModel(ComRelaBO.class , param);
		comRelaService.deleteObject(comRelaBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======ComRelaAction--findOneComRela======>");
		
		ComRelaBO comRelaBO = BaseDto.toModel(ComRelaBO.class , param);
		return comRelaService.findOne(comRelaBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======ComRelaAction--findAllComRela======>");
				
		return comRelaService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======ComRelaAction--queryComRelaForPage======>");
		
		return comRelaService.queryForPage(currentPage);
	}	
}
