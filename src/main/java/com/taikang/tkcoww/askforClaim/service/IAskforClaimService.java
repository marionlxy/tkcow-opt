package com.taikang.tkcoww.askforClaim.service;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.IBaseService;

/**
  * IAskforClaimService
  */
 
 public interface IAskforClaimService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "askforClaimService";  	
 	public CurrentPage getAskforClaimList(CurrentPage page);
	public List<Dto> getAskforClaimListDto(Dto param);
 }
 
 
 