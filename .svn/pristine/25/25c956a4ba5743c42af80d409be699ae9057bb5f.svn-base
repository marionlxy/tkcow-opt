package com.taikang.udp.manage.action.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.accessory.service.IAccessoryService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.manage.action.IAdvertAction;
import com.taikang.udp.manage.model.AdvertBO;
import com.taikang.udp.manage.model.AdvertPositionBO;
import com.taikang.udp.manage.model.ArticleClassBO;
import com.taikang.udp.manage.service.IAdvertPositionService;
import com.taikang.udp.manage.service.IAdvertService;

/**
  * AdvertAction
  */
  @Service(IAdvertAction.ACTION_ID)
public class AdvertActionImpl extends BaseActionImpl 
  implements IAdvertAction {

    /**
      * 注入service
      */
    @Resource(name=IAdvertService.SERVICE_ID)
	private IAdvertService<AdvertBO> advertService;	
    
    @Resource(name=IAccessoryService.SERVICE_ID)
	private IAccessoryService<AccessoryBO> accessoryService;
    
    @Resource(name=IAdvertPositionService.SERVICE_ID)
	private IAdvertPositionService<AdvertPositionBO> advertPositionService;
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======AdvertAction--addAdvert======>");
		
		AdvertBO advertBO = BaseDto.toModel(AdvertBO.class , param);
		advertService.insertObject(advertBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======AdvertAction--updateAdvert======>");
		
		AdvertBO advertBO = BaseDto.toModel(AdvertBO.class , param);
		advertService.updateObject(advertBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======AdvertAction--deleteAdvert======>");
		
		AdvertBO advertBO = BaseDto.toModel(AdvertBO.class , param);
		advertService.deleteObject(advertBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======AdvertAction--findOneAdvert======>");
		
		AdvertBO advertBO = BaseDto.toModel(AdvertBO.class , param);
		return advertService.findOne(advertBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======AdvertAction--findAllAdvert======>");
				
		return advertService.findAllMap(param);
	} 
	
	/**
     * 查询所有数据
     */
	public List<AdvertBO> findAll(AdvertBO param) {
		List<AdvertBO> list = advertService.findAll(param);	
		for(AdvertBO bo:list){
			fillBO(bo);
		}
		return list;
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======AdvertAction--queryAdvertForPage======>");
		
		return advertService.queryForPage(currentPage);
	}
	
	private void fillBO(AdvertBO bo){
		AccessoryBO accessory = new AccessoryBO();
		accessory.setId(bo.getAdAccId());
		accessory = accessoryService.findOne(accessory);
		bo.setAd_acc(accessory);
		AdvertPositionBO ap = new AdvertPositionBO();
		ap.setId(bo.getAdApId());
		ap = advertPositionService.findOne(ap);
		bo.setAd_ap(ap);
	}

	@Override
	public AdvertBO findOne(AdvertBO param) {
		logger.debug("<======PartnerAction--findAll======>");
		AdvertBO bo = advertService.findOne(param);
		if(bo != null){
			fillBO(bo);
		}
		return bo;
	}

	@Override
	public void insertObject(AdvertBO param) {
		advertService.insertObject(param);
	}

	@Override
	public void updateObject(AdvertBO param) {
		advertService.updateObject(param);
	}

	@Override
	public void decorateAdvertList(List<Dto> list) {
		AdvertPositionBO apbo = new AdvertPositionBO();
		if(list.size() > 0){
			for(Dto dto:list){
				apbo.setId(dto.getAsLong("ad_ap_id"));
				AdvertPositionBO _bo = advertPositionService.findOne(apbo);
				dto.put("ad_ap", _bo);
			}
		}
	}	
			
}
