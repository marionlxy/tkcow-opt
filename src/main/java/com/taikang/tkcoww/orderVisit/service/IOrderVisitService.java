package com.taikang.tkcoww.orderVisit.service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.IBaseService;

import java.util.Arrays;
import java.util.List;

/**
  * IOrderVisitService
  */
 
 public interface IOrderVisitService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "orderVisitService";  
	public CurrentPage getOrderVisitList(CurrentPage page);
	public List<Dto> getvisitList(Dto param);
	public List findAllT(Dto dtoDate);
 }
 
 
 