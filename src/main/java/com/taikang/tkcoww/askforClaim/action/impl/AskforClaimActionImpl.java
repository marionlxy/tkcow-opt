package com.taikang.tkcoww.askforClaim.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.tkcoww.askforClaim.model.AskforClaimBO;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.tkcoww.askforClaim.service.IAskforClaimService;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.askforClaim.action.IAskforClaimAction;

/**
  * AskforClaimAction
  */
  @Service(IAskforClaimAction.ACTION_ID)
public class AskforClaimActionImpl extends BaseActionImpl 
  implements IAskforClaimAction {

    /**
      * 注入service
      */
    @Resource(name=IAskforClaimService.SERVICE_ID)
	private IAskforClaimService<AskforClaimBO> askforClaimService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======AskforClaimAction--addAskforClaim======>");
		
		AskforClaimBO askforClaimBO = BaseDto.toModel(AskforClaimBO.class , param);
		askforClaimService.insertObject(askforClaimBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======AskforClaimAction--updateAskforClaim======>");
		
		AskforClaimBO askforClaimBO = BaseDto.toModel(AskforClaimBO.class , param);
		askforClaimService.updateObject(askforClaimBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======AskforClaimAction--deleteAskforClaim======>");
		
		AskforClaimBO askforClaimBO = BaseDto.toModel(AskforClaimBO.class , param);
		askforClaimService.deleteObject(askforClaimBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======AskforClaimAction--findOneAskforClaim======>");
		
		AskforClaimBO askforClaimBO = BaseDto.toModel(AskforClaimBO.class , param);
		return askforClaimService.findOne(askforClaimBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======AskforClaimAction--findAllAskforClaim======>");
				
		return askforClaimService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======AskforClaimAction--queryAskforClaimForPage======>");
		
		return askforClaimService.queryForPage(currentPage);
	}

	@Override
	public CurrentPage getAskforClaimList(CurrentPage page) {
        logger.debug("<======AskforClaimAction--getAskforClaimList======>");
		
		return askforClaimService.getAskforClaimList(page);
	}

	@Override
	public List<Dto> getAskforClaimListDto(Dto param) {
       logger.debug("<======AskforClaimAction--getAskforClaimListDto======>");
		
		return askforClaimService.getAskforClaimListDto(param);
	}	
}
