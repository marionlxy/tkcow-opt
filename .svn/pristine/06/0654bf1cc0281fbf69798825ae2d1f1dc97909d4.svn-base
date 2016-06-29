package com.taikang.udp.manage.action.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.accessory.service.IAccessoryService;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.manage.action.IAdvertAction;
import com.taikang.udp.manage.action.IAdvertPositionAction;
import com.taikang.udp.manage.model.AdvertBO;
import com.taikang.udp.manage.model.AdvertPositionBO;
import com.taikang.udp.manage.service.IAdvertPositionService;
import com.taikang.udp.manage.service.IAdvertService;

/**
  * AdvertPositionAction
  */
  @Service(IAdvertPositionAction.ACTION_ID)
public class AdvertPositionActionImpl extends BaseActionImpl 
  implements IAdvertPositionAction {

    /**
      * 注入service
      */
    @Resource(name=IAdvertPositionService.SERVICE_ID)
	private IAdvertPositionService<AdvertPositionBO> advertPositionService;
    
    @Resource(name=IAccessoryService.SERVICE_ID)
   	private IAccessoryService<AccessoryBO> accessoryService;
    
    @Resource(name=IAdvertAction.ACTION_ID)
	private IAdvertAction advertAction;
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======AdvertPositionAction--addAdvertPosition======>");
		
		AdvertPositionBO advertPositionBO = BaseDto.toModel(AdvertPositionBO.class , param);
		advertPositionService.insertObject(advertPositionBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======AdvertPositionAction--updateAdvertPosition======>");
		
		AdvertPositionBO advertPositionBO = BaseDto.toModel(AdvertPositionBO.class , param);
		advertPositionService.updateObject(advertPositionBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======AdvertPositionAction--deleteAdvertPosition======>");
		
		AdvertPositionBO advertPositionBO = BaseDto.toModel(AdvertPositionBO.class , param);
		advertPositionService.deleteObject(advertPositionBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======AdvertPositionAction--findOneAdvertPosition======>");
		
		AdvertPositionBO advertPositionBO = BaseDto.toModel(AdvertPositionBO.class , param);
		return advertPositionService.findOne(advertPositionBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======AdvertPositionAction--findAllAdvertPosition======>");
				
		return advertPositionService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======AdvertPositionAction--queryAdvertPositionForPage======>");
		
		return advertPositionService.queryForPage(currentPage);
	}
	
	/**
     * 查询单条数据
     */
	public AdvertPositionBO findOne(AdvertPositionBO param) {
		logger.debug("<======AdvertPositionAction--findOneAdvertPosition======>");
		AdvertPositionBO bo =  advertPositionService.findOne(param);
		AccessoryBO accessory = new AccessoryBO();
		accessory.setId(bo.getApAccId());
		accessory = accessoryService.findOne(accessory);
		bo.setAp_acc(accessory);
		return bo;
	}

	@Override
	public void insertObject(AdvertPositionBO param) {
		advertPositionService.insertObject(param);
	}
	
	@Override
	public void updateObject(AdvertPositionBO param) {
		advertPositionService.updateObject(param);
	}

	@Override
	public void decorateAdvertPositionBO(AdvertPositionBO ap) {
		    List<AdvertBO> advs = new ArrayList<AdvertBO>();
		    AdvertBO advert = new AdvertBO();
	        advert.setAdApId(ap.getId());
	        
	        List<AdvertBO> _advs = advertAction.findAll(advert);
	        
	        for (AdvertBO temp_adv : _advs) {
	            if (temp_adv.getAdStatus() == 1
	                    && temp_adv.getAdBeginTime().before(new Date())
	                    && temp_adv.getAdEndTime().after(new Date())) {
	                advs.add(temp_adv);
	            }
	        }
	        if (advs.size() > 0) {
	            if (ap.getApType().equals("text")) {
	                if (ap.getApShowType() == 0) {// 固定广告
	                    ap.setApText(advs.get(0).getAdText());
	                    ap.setApAccUrl(advs.get(0).getAdUrl());
	                    ap.setAdv_id(advs.get(0).getId());
	                }
	                if (ap.getApShowType() == 1) {// 随机广告
	                    Random random = new Random();
	                    int i = random.nextInt(advs.size());
	                    ap.setApText(advs.get(i).getAdText());
	                    ap.setApAccUrl(advs.get(i).getAdUrl());
	                    ap.setAdv_id(advs.get(i)
	                            .getId());
	                }
	            }
	            if (ap.getApType().equals("img")) {
	                if (ap.getApShowType() == 0) {// 固定广告
	                    ap.setAp_acc(advs.get(0).getAd_acc());
	                    ap.setApAccUrl(advs.get(0).getAdUrl());
	                    ap.setAdv_id(advs.get(0).getId());
	                }
	                if (ap.getApShowType() == 1) {// 随机广告
	                    Random random = new Random();
	                    int i = random.nextInt(advs.size());
	                    ap.setAp_acc(advs.get(i).getAd_acc());
	                    ap.setApAccUrl(advs.get(i).getAdUrl());
	                    ap.setAdv_id(advs.get(i).getId());
	                }
	            }
	            if (ap.getApType().equals("slide")) {
	                if (ap.getApShowType() == 0) {// 固定广告
	                    ap.setAdvs(advs);
	                }
	                if (ap.getApShowType() == 1) {// 随机广告
	                    
	                    Set<Integer> list = CommUtil.randomInt(advs.size(),
	                            8);
	                    for (int i : list) {
	                        ap.getAdvs().add(advs.get(i));
	                    }
	                }
	            }
	            if (ap.getApType().equals("scroll")) {
	                if (ap.getApShowType() == 0) {// 固定广告
	                    ap.setAdvs(advs);
	                }
	                if (ap.getApShowType() == 1) {// 随机广告
	                    Set<Integer> list = CommUtil.randomInt(advs.size(),
	                            12);
	                    for (int i : list) {
	                        ap.getAdvs().add(advs.get(i));
	                    }
	                    
	                   /* @SuppressWarnings("unchecked")
	                    List<Advert> _advertList = CommUtil.toRowChildList(ap.getAdvs(),4);
	                    ap.setAdvs(_advertList);*/
	                }
	            }
	        } else {
	           
	            AdvertBO adv = new AdvertBO();
	            adv.setAdUrl(ap.getApAccUrl());
	            adv.setAd_acc(ap.getAp_acc());
	            ap.getAdvs().add(adv);
	        }
		
	}

	
}
