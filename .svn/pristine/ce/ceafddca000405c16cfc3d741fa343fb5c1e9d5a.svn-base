package com.taikang.tkcoww.askforClaim.service.impl;
 
import com.taikang.tkcoww.askforClaim.model.AskforClaimBO;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.tkcoww.askforClaim.service.IAskforClaimService;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * AskforClaimServiceImpl
  */
 @Service(IAskforClaimService.SERVICE_ID)
 public class AskforClaimServiceImpl extends BaseServiceImpl 
 implements IAskforClaimService<AskforClaimBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(AskforClaimBO askforClaim) {
		logger.debug("<======AskforClaimServiceImpl--insertAskforClaim======>");		
		appDao.insert("AskforClaim.addAskforClaim",askforClaim);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(AskforClaimBO askforClaim) {
		logger.debug("<======AskforClaimServiceImpl--updateAskforClaim======>");
		appDao.update("AskforClaim.updateAskforClaim",askforClaim);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(AskforClaimBO askforClaim) {
		logger.debug("<======AskforClaimServiceImpl--deleteAskforClaim======>");
		appDao.delete("AskforClaim.deleteAskforClaim",askforClaim);
	}
	
	/**
      * 查询数据
      */
	public AskforClaimBO findOne(AskforClaimBO askforClaim) {
		logger.debug("<======AskforClaimServiceImpl--findAskforClaim======>");
		
		AskforClaimBO askforClaimBackBO=appDao.queryOneObject("AskforClaim.findOneAskforClaim", askforClaim);
		return askforClaimBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<AskforClaimBO> findAll(AskforClaimBO  askforClaim) {
		logger.debug("<======AskforClaimServiceImpl--findAllAskforClaim======>");
		return appDao.queryForEntityList("AskforClaim.findAllAskforClaim", askforClaim);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======AskforClaimServiceImpl--queryAskforClaimForPage======>");
		return appDao.queryForPage("AskforClaim.queryAskforClaimForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======AskforClaimServiceImpl--findAllMapAskforClaim======>");		
		return appDao.queryForMapList("AskforClaim.findAllMapAskforClaim", param);
	}

	@Override
	public CurrentPage getAskforClaimList(CurrentPage page) {
		logger.debug("<======AskforClaimServiceImpl--getAskforClaimList======>");
		return appDao.queryForPage("AskforClaim.getAskforClaimList", page);
	}

	@Override
	public List<Dto> getAskforClaimListDto(Dto param) {
		logger.debug("<======AskforClaimServiceImpl--getAskforClaimListDto======>");		
		return appDao.queryForMapList("AskforClaim.getAskforClaimListDto", param);
	}
 }
  