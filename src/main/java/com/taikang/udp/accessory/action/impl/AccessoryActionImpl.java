package com.taikang.udp.accessory.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.udp.accessory.action.IAccessoryAction;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.accessory.service.IAccessoryService;

/**
  * AccessoryAction
  */
  @Service(IAccessoryAction.ACTION_ID)
public class AccessoryActionImpl extends BaseActionImpl 
  implements IAccessoryAction {

    /**
      * 注入service
      */
    @Resource(name=IAccessoryService.SERVICE_ID)
	private IAccessoryService<AccessoryBO> accessoryService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======AccessoryAction--addAccessory======>");
		
		AccessoryBO accessoryBO = BaseDto.toModel(AccessoryBO.class , param);
		accessoryService.insertObject(accessoryBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======AccessoryAction--updateAccessory======>");
		
		AccessoryBO accessoryBO = BaseDto.toModel(AccessoryBO.class , param);
		accessoryService.updateObject(accessoryBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======AccessoryAction--deleteAccessory======>");
		
		AccessoryBO accessoryBO = BaseDto.toModel(AccessoryBO.class , param);
		accessoryService.deleteObject(accessoryBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======AccessoryAction--findOneAccessory======>");
		
		AccessoryBO accessoryBO = BaseDto.toModel(AccessoryBO.class , param);
		return accessoryService.findOne(accessoryBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======AccessoryAction--findAllAccessory======>");
				
		return accessoryService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======AccessoryAction--queryAccessoryForPage======>");
		
		return accessoryService.queryForPage(currentPage);
	}	
}
