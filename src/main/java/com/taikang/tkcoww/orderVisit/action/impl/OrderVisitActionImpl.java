package com.taikang.tkcoww.orderVisit.action.impl;


import com.taikang.tkcoww.orderVisit.action.IOrderVisitAction;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.tkcoww.orderVisit.model.OrderVisitBO;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.tkcoww.orderVisit.service.IOrderVisitService;
import org.springframework.stereotype.Service;

/**
  * OrderVisitAction
  */
  @Service(IOrderVisitAction.ACTION_ID)
public class OrderVisitActionImpl extends BaseActionImpl 
  implements IOrderVisitAction {

    /**
      * 注入service
      */
    @Resource(name=IOrderVisitService.SERVICE_ID)
	private IOrderVisitService<OrderVisitBO> orderVisitService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======OrderVisitAction--addOrderVisit======>");
		
		OrderVisitBO orderVisitBO = BaseDto.toModel(OrderVisitBO.class , param);
		orderVisitService.insertObject(orderVisitBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======OrderVisitAction--updateOrderVisit======>");
		
		OrderVisitBO orderVisitBO = BaseDto.toModel(OrderVisitBO.class , param);
		orderVisitService.updateObject(orderVisitBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======OrderVisitAction--deleteOrderVisit======>");
		
		OrderVisitBO orderVisitBO = BaseDto.toModel(OrderVisitBO.class , param);
		orderVisitService.deleteObject(orderVisitBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======OrderVisitAction--findOneOrderVisit======>");
		
		OrderVisitBO orderVisitBO = BaseDto.toModel(OrderVisitBO.class , param);
		return orderVisitService.findOne(orderVisitBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======OrderVisitAction--findAllOrderVisit======>");
				
		return orderVisitService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======OrderVisitAction--queryOrderVisitForPage======>");
		
		return orderVisitService.queryForPage(currentPage);
	}

	@Override
	public CurrentPage getOrderVisitList(CurrentPage page) {
      logger.debug("<======OrderVisitAction--getOrderVisitList======>");
		
		return orderVisitService.getOrderVisitList(page);
	}

	@Override
	public List<Dto> getvisitList(Dto param) {
	 logger.debug("<======OrderVisitAction--getvisitList======>");
	 return  orderVisitService.getvisitList(param);
	}

	@Override
	public List findAllT(Dto dtoDate) {
		logger.debug("<======OrderVisitAction--findAllT======>");
		
		return orderVisitService.findAllT(dtoDate);
	}	
}
